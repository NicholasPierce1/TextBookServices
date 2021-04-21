class TestClass {

    #y = 3;

    #x = "test";

    static #num1 = 3;

    static #num2 = 4;

    static array = [
        {
            "value": TestClass.#num1,
            "type": typeof(TestClass.#num1)
        }
    ]

    static arrayLength = TestClass.array.length;

    TestClass(){

    }

    getY(){return this.#y;}

    getX(){return this.#x;}


}