from flask import Flask, render_template
import os

app = Flask(__name__)

@app.route('/')
def landing():
    print(os.getcwd())
    return render_template("landing.html")

if __name__ == "__main__":
    app.run(debug=True)
