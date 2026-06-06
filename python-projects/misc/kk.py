import random

def guess_number():
    # 生成1-100之间的随机数
    target = random.randint(1, 100)
    attempts = 0
    
    print("欢迎来到猜数字游戏！")
    print("我已经生成了一个1-100之间的数字，快来猜猜看~")
    
    while True:
        try:
            guess = int(input("请输入你的猜测："))
            attempts += 1
            
            if guess < target:
                print("太小啦，再大一点！")
            elif guess > target:
                print("太大啦，再小一点！")
            else:
                print(f"恭喜你猜对了！你一共猜了 {attempts} 次。")
                break
        except ValueError:
            print("请输入有效的数字哦！")

if __name__ == "__main__":
    guess_number()
