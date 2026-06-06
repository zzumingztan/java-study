# -*- coding: utf-8 -*-
"""Java Review Assistant - Flask Backend"""
import json
import os
import sys
import io

# Fix encoding on Windows
sys.stdout = io.TextIOWrapper(sys.stdout.buffer, encoding='utf-8', errors='replace')

from flask import Flask, render_template, jsonify, request

app = Flask(__name__)

# Load data
DATA_DIR = os.path.dirname(os.path.abspath(__file__))
with open(os.path.join(DATA_DIR, "learning_data.json"), "r", encoding="utf-8") as f:
    learning_data = json.load(f)


@app.route("/")
def index():
    """Serve the main page."""
    chapters = []
    for num, ch in sorted(learning_data["chapters"].items(), key=lambda x: int(x[0])):
        chapters.append({
            "number": int(num),
            "title": ch["title"],
            "filename": ch.get("filename", ""),
            "slides_count": ch.get("slides_count", 0),
            "summary_preview": ch["summary"][:100] + "...",
            "flashcard_count": len(ch.get("flashcards", [])),
            "quiz_count": len(ch.get("quiz", [])),
        })
    return render_template("index.html", chapters=chapters, meta=learning_data["meta"])


@app.route("/api/chapter/<int:chapter_num>")
def get_chapter(chapter_num):
    """Return full chapter data."""
    ch = learning_data["chapters"].get(str(chapter_num))
    if not ch:
        return jsonify({"error": "Chapter not found"}), 404
    return jsonify(ch)


@app.route("/api/chapters")
def list_chapters():
    """List all chapters with basic info."""
    chapters = []
    for num, ch in sorted(learning_data["chapters"].items(), key=lambda x: int(x[0])):
        chapters.append({
            "number": int(num),
            "title": ch["title"],
            "flashcard_count": len(ch.get("flashcards", [])),
            "quiz_count": len(ch.get("quiz", [])),
        })
    return jsonify(chapters)


if __name__ == "__main__":
    print("=" * 60)
    print("Java 学习助手已启动!")
    print("共加载 {} 个章节".format(len(learning_data['chapters'])))
    print("打开浏览器访问: http://127.0.0.1:5000")
    print("=" * 60)
    app.run(debug=True, host="127.0.0.1", port=5000)
