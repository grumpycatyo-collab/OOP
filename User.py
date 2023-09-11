class User:
    def __init__(self):
        self.name = ""
        self.surname = ""
        self.id = ""
        self.base_of_residence = None
        self.is_resident = False
        self.birthday = None
        self.role = ""

    def get_name(self):
        return self.name

    def set_name(self, name):
        self.name = name

    def get_surname(self):
        return self.surname

    def set_surname(self, surname):
        self.surname = surname

    def get_id(self):
        return self.id

    def set_id(self, id):
        self.id = id

    def get_base_of_residence(self):
        return self.base_of_residence

    def set_base_of_residence(self, base_of_residence):
        self.base_of_residence = base_of_residence

    def is_resident(self):
        return self.is_resident

    def set_resident(self, is_resident):
        self.is_resident = is_resident

    def get_birthday(self):
        return self.birthday

    def set_birthday(self, birthday):
        self.birthday = birthday

    def get_role(self):
        return self.role

    def set_role(self, role):
        self.role = role