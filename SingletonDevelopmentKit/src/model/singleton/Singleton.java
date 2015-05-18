package model.singleton;

import model.data.SerializableSingleton;
import model.data.SingletonSerialization;

public interface Singleton< S extends SerializableSingleton< ? > > extends SingletonSerialization< S >, SerializableSingleton< S >{

}
