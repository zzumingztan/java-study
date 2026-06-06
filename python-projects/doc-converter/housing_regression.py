import paddle
import numpy as np
import matplotlib.pyplot as plt

# 设置paddle默认的全局数据类型为float64
paddle.set_default_dtype('float64')

# 加载数据
train_dataset = paddle.text.datasets.UCIHousing(mode='train')
eval_dataset = paddle.text.datasets.UCIHousing(mode='test')

# 封装训练数据
train_loader = paddle.io.DataLoader(train_dataset, batch_size=32, shuffle=True)
eval_loader = paddle.io.DataLoader(eval_dataset, batch_size=8, shuffle=False)

print("步骤一完成：数据已加载并封装完毕！")

# 定义模型架构
class Regressor(paddle.nn.Layer):
    def __init__(self):
        super(Regressor, self).__init__()
        # 定义一层全连接网络，输入13个特征，输出1个预测值，不需要激活函数
        self.fc = nn.Linear(in_features=13, out_features=1)

    def forward(self, inputs):
        # 前向计算过程
        x = self.fc(inputs)
        return x

# 实例化模型
model = Regressor()
paddle.summary(model, (32, 13), dtypes='float64')
print("步骤二完成：全连接网络模型架构已搭建并实例化！")

Batch = 0
# 记录批次
Batchs = []
# 记录损失值，用于后续绘图
all_train_loss = []

# 模型实例化
model = Regressor()
# 训练模型
model.train()
# 均方误差损失函数
mse_loss = paddle.nn.MSELoss()
# 优化函数，需要传入学习率和模型参数
opt = paddle.optimizer.SGD(learning_rate=0.0005, parameters=model.parameters())

# 迭代次数
epochs_num = 200
for pass_num in range(epochs_num):
    for batch_id, data in enumerate(train_loader()):
        # data包括input, label
        image = data[0]
        label = data[1]

        # 前向计算
        predict = model(image)
        loss = mse_loss(predict, label)

        # 10次迭代记录一次损失值
        if batch_id != 0 and batch_id % 10 == 0:
            Batch = Batch + 10
            Batchs.append(Batch)
            # 记录损失值并打印
            all_train_loss.append(loss.numpy()[0])
            print('epoch:{}, step:{}, train_loss:{}'.format(pass_num, batch_id, loss.numpy()[0]))

        # 反向传播
        loss.backward()
        # 更新参数
        opt.step()
        # 重置梯度
        opt.clear_grad()

# 保存模型
paddle.save(model.state_dict(), 'Regressor')
print("步骤三完成：模型训练结束，参数已保存！")

# 绘制训练损失值随迭代次数的变化
def draw_train_loss(Batchs, train_losses):
    plt.title('training loss', fontsize=24)
    plt.xlabel('batch', fontsize=14)
    plt.ylabel('loss', fontsize=14)
    plt.plot(Batchs, train_losses, color='green', label='training loss')
    plt.legend()
    plt.grid()
    plt.savefig('training_loss.png')  # 保存图像
    plt.show()

draw_train_loss(Batchs, all_train_loss)

# 加载模型进行评估
para_state_dict = paddle.load('Regressor')
model = Regressor()
model.set_state_dict(para_state_dict)
model.eval()

# 收集测试集的预测结果与真实值
infer_results, ground_truths = [], []
for batch_id, data in enumerate(eval_loader()):
    predict = model(data[0])
    infer_results.extend(predict.numpy().flatten())
    ground_truths.extend(data[1].numpy().flatten())

# 绘制真实值和预测值对比图
def draw_infer_result(ground_truths, infer_results):
    plt.title('Boston', fontsize=24)
    plt.xlabel('ground truth', fontsize=14)
    plt.ylabel('infer result', fontsize=14)
    plt.scatter(ground_truths, infer_results, color='red', label='prediction')
    # 绘制 y=x 参考线
    plt.plot([0, 30], [0, 30], color='blue')
    plt.grid()
    plt.savefig('infer_result.png')  # 保存图像
    plt.show()

draw_infer_result(ground_truths, infer_results)