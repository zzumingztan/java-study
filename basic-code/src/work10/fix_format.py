# -*- coding: utf-8 -*-
"""Fix all formatting in the document to match task requirements."""
import sys, os, re
sys.stdout.reconfigure(encoding='utf-8')

workdir = r'F:\IDE\study\basic-code\src\work10'
src = os.path.join(workdir, '202415060613(10)李润豪.doc')

import pythoncom, win32com.client
pythoncom.CoInitialize()
word = win32com.client.DispatchEx("Word.Application")
word.Visible = False
word.DisplayAlerts = False

doc = word.Documents.Open(src)

# ============================================================
# STEP 1: Find section boundaries (do this BEFORE any modifications)
# ============================================================
sec4_start = None
sec5_start = None
sec6_start = None
total = doc.Paragraphs.Count

for i in range(1, total + 1):
    text = doc.Paragraphs(i).Range.Text.strip()
    if text in ('实验结果', '四、实验结果'):
        sec4_start = i
    elif text == '五、问题总结':
        sec5_start = i
    elif text == '六、心得体会':
        sec6_start = i

print(f"Found: 四(实验结果)={sec4_start}, 五={sec5_start}, 六={sec6_start}")
assert sec4_start and sec5_start and sec6_start, "Missing section boundaries!"

# Fix the section header text
doc.Paragraphs(sec4_start).Range.Text = '四、实验结果'

# ============================================================
# STEP 2: Helper function
# ============================================================
def fmt(para, font_name=None, font_size=None, bold=None,
        indent=None, alignment=None, line_spacing=None):
    """Apply formatting to a paragraph."""
    rng = para.Range
    if font_name is not None:
        rng.Font.Name = font_name
    if font_size is not None:
        rng.Font.Size = font_size
    if bold is not None:
        rng.Font.Bold = bold
    if indent is not None:
        rng.ParagraphFormat.FirstLineIndent = indent
    if alignment is not None:
        rng.ParagraphFormat.Alignment = alignment
    if line_spacing is not None:
        rng.ParagraphFormat.LineSpacingRule = 3  # wdLineSpaceExactly
        rng.ParagraphFormat.LineSpacing = line_spacing

def looks_like_code(text):
    """Check if text looks like Java source code."""
    if not text:
        return False
    code_starts = [
        '/**', '*/', ' * ', '//', '/*',
        'public ', 'private ', 'protected ', 'import ',
        'package ', '@Override', 'System.', 'this.',
        'extends ', 'implements ',
        'int ', 'long ', 'void ', 'class ', 'new ',
        'for (', 'while (', 'if (', '} catch',
        'try {', 'return ', 'throw new', 'else ',
    ]
    for s in code_starts:
        if text.startswith(s) or s in text:
            return True
    # Also check for Java-like patterns
    if re.match(r'^\s*[})];?\s*$', text):
        return True
    if re.match(r'^\s*\w+\s+\w+\s*[=;(]', text) and not text.startswith('在') and not text.startswith('通'):
        return True
    return False

def looks_like_output(text):
    """Check if text looks like console output."""
    if not text:
        return False
    markers = [
        '============', '-----', '--- ', '===== ',
        '【Thread类】', '【Runnable接口】',
        '! = ', ' 实验十：', ' 多线程计算', ' 实验完成',
        '方式一：', '方式二：',
    ]
    for m in markers:
        if m in text:
            return True
    if re.match(r'^\d+! = \d+$', text.strip()):
        return True
    return False

# ============================================================
# STEP 3: Fix Section 4 - 实验结果
# ============================================================
print("\n=== Section 4: 实验结果 ===")

# Header
fmt(doc.Paragraphs(sec4_start), font_name='宋体', font_size=12, bold=True,
    indent=0, alignment=3, line_spacing=20)

in_code = False
in_output = False
count = 0

for i in range(sec4_start + 1, sec5_start):
    try:
        para = doc.Paragraphs(i)
    except:
        continue
    text = para.Range.Text.strip()

    if not text:
        in_code = False
        in_output = False
        continue

    # 1. Code block titles
    if '代码清单' in text:
        fmt(para, font_name='宋体', font_size=10.5, bold=False,
            indent=0, alignment=1, line_spacing=12)
        in_code = True
        in_output = False
        count += 1
        continue

    # 2. Code content
    if in_code and looks_like_code(text):
        fmt(para, font_name='Times New Roman', font_size=10.5, bold=False,
            indent=0, alignment=0, line_spacing=12)
        count += 1
        continue
    else:
        in_code = False

    # 3. Output text
    if looks_like_output(text):
        fmt(para, font_name='Times New Roman', font_size=10.5, bold=False,
            indent=0, alignment=0, line_spacing=12)
        in_output = True
        count += 1
        continue
    elif in_output and not looks_like_output(text):
        in_output = False

    # Check if still code (after code title, some lines might not match)
    if looks_like_code(text):
        fmt(para, font_name='Times New Roman', font_size=10.5, bold=False,
            indent=0, alignment=0, line_spacing=12)
        count += 1
        continue

    # 4. Figure caption
    if text.startswith('图10.'):
        fmt(para, font_name='宋体', font_size=10.5, bold=False,
            indent=0, alignment=1, line_spacing=12)
        count += 1
        continue

    # 5. Screenshot placeholder
    if '此处插入' in text:
        fmt(para, font_name='宋体', font_size=10.5, bold=False,
            indent=0, alignment=1, line_spacing=12)
        count += 1
        continue

    # 6. Sub-headings like "1. xxx", "2. xxx", "4. xxx"
    if re.match(r'^[1-4]\.\s', text):
        fmt(para, font_name='宋体', font_size=12, bold=True,
            indent=0, alignment=3, line_spacing=20)
        count += 1
        continue

    # 7. Everything else: body text
    fmt(para, font_name='宋体', font_size=12, bold=False,
        indent=24, alignment=3, line_spacing=20)
    count += 1

print(f"  Fixed {count} paragraphs")

# ============================================================
# STEP 4: Fix Section 5 - 问题总结
# ============================================================
print("\n=== Section 5: 问题总结 ===")

fmt(doc.Paragraphs(sec5_start), font_name='宋体', font_size=12, bold=True,
    indent=0, alignment=3, line_spacing=20)

count = 0
for i in range(sec5_start + 1, sec6_start):
    try:
        para = doc.Paragraphs(i)
    except:
        continue
    text = para.Range.Text.strip()
    if not text:
        continue

    # Problem number titles: "1. xxx", etc.
    if re.match(r'^[1-4]\.\s', text):
        fmt(para, font_name='宋体', font_size=12, bold=False,
            indent=0, alignment=3, line_spacing=20)
    else:
        fmt(para, font_name='宋体', font_size=12, bold=False,
            indent=24, alignment=3, line_spacing=20)
    count += 1

print(f"  Fixed {count} paragraphs")

# ============================================================
# STEP 5: Fix Section 6 - 心得体会
# ============================================================
print("\n=== Section 6: 心得体会 ===")

fmt(doc.Paragraphs(sec6_start), font_name='宋体', font_size=12, bold=True,
    indent=0, alignment=3, line_spacing=20)

count = 0
for i in range(sec6_start + 1, doc.Paragraphs.Count + 1):
    try:
        para = doc.Paragraphs(i)
    except:
        continue
    text = para.Range.Text.strip()
    if not text:
        continue

    fmt(para, font_name='宋体', font_size=12, bold=False,
        indent=24, alignment=3, line_spacing=20)
    count += 1

print(f"  Fixed {count} paragraphs")

# ============================================================
# Save
# ============================================================
print("\n=== Saving ===")
doc.Save()
doc.Close()
word.Quit()
pythoncom.CoUninitialize()
print("Done! All formatting fixed.")
PYEOF