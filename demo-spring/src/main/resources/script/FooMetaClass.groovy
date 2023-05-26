package script

class FooMetaClass extends DelegatingMetaClass {
    FooMetaClass(MetaClass metaClass) { super(metaClass); }
    Object invokeMethod(Object object, String name, Object[] args) {
        super.invokeMethod(object,name,args).toUpperCase();
    }
}
