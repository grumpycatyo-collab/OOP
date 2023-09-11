class Diamond:
    def __init__(self, size):
        self.size = size
    def print_diamond(self):
        if self.size % 2 == 0:
            self.size += 1 

        for i in range(self.size):
            if i < self.size // 2:
                print(' ' * (self.size // 2 - i) + '*' * (2 * i + 1))
            elif i == self.size // 2:
                print('*' * self.size)
            else:
                print(' ' * (i - self.size // 2) + '*' * (2 * (self.size - i) - 1))


