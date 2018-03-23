from PIL import Image

im = Image.open('temp.png').convert('RGBa')
w, h = im.size

def makeString(original):
    ori = hex(int(original, 2))[2:]
    ori = ori[::-1]
    while len(ori) < w/4:
        ori += "0"
    ori = ori[::-1]
    return ori

def calcArray(array):
    hexArray = []
    
    for y in range(h):
        binString = ""
        for x in range(w):
            binString += array[x][y]
        hexArray.append(makeString(binString))
    return hexArray

def getImage():
    array = [["0"]*w for i in range(h)]
    for y in range(h):
        for x in range(w):
            r, g, b, a = im.getpixel((x, y))
            if a > 200:
                array[x][y] = "1"
    hexArray = calcArray(array)
    return hexArray
    
def execute():
    image = getImage()
    for px in image:
        print(px)

execute()
