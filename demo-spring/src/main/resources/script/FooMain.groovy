package script

def getBar(){
    def f1 = new Foo()
    def f2 = new Foo()
    f2.metaClass = new FooMetaClass(f2.metaClass)
    println(f1.bar()+"-"+f2.bar())
    assert f1.bar() == "bar"
    assert f2.bar() == "BAR"
    assert f1.metaClass =~ /MetaClassImpl/
    assert f2.metaClass =~ /FooMetaClass/
    assert f1.class.toString() == "class script.Foo"
    assert f2.class.toString() == "class script.Foo"
}
