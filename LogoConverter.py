from sys import argv
from PIL import Image


class LogoConverter:
    def __init__(self, img_path):
        self.source_image = Image.open(img_path).convert('RGBa')
        self.width, self.height = self.source_image.size

    def binary_array_to_hex_array(self, binary_array):
        hex_array = []
        for line in binary_array:
            hex_string = hex(int(line, 2))[2:]
            hex_array.append(hex_string.zfill(int(self.width/4)))
        return hex_array

    def black_pixels_to_array(self):
        pixel_array = []
        for y in range(self.height):
            pixel_array.append('')
            for x in range(self.width):
                _, _, _, a = self.source_image.getpixel((x, y))
                pixel_array[y] += str(int(a > 200))
        return pixel_array

    def convert_image(self):
        pixel_array = self.black_pixels_to_array()
        return self.binary_array_to_hex_array(pixel_array)


if __name__ == '__main__':
    converter = LogoConverter(argv[1])
    for line in converter.convert_image():
        print(line)
