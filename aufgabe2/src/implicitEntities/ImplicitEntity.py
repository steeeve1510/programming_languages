from elements.Entity import Entity

class ImplicitEntity(Entity):

    def __init__(self, code):
        super(ImplicitEntity, self).__init__(code)
        self.isImplicit = True;