import kotlin.String
import kotlin.text.*


fun switchLowerAndUpperCases(value: String) : String
{
    var newString = ""
    for(i in value)
    {
        if(i.isLowerCase())
        {
            newString += i.uppercaseChar() /////neviem prečo sa mi to prečiarklo i.toUppercase
        }
        else if (i.isUpperCase())
        {
            newString += i.lowercaseChar()    ////tu takisto
        }
        else if(i.isWhitespace())
        {
            newString += "*"
        }
        else
        {
            newString += i
        }
    }
    return newString
}

enum class Planets(val weight: Long, val sunDistance: Long, val sunRotation: Long)
{
    MERCURY(65487645, 75847845, 454685),
    VENUS(64534654685,54644563,654653465465),
    EARTH(9864532, 8945612,895686538965),
    MARS(68532,23546879,9856365431253),
    JUPITER(87695312, 768548,21346587),
    SATURN(89745132,2534687, 15468745312845),
    URANUS(7865347685321, 536875468745, 8746568568658658),
    NEPTUNE(124568764512, 124568798645312,1546879864512111993)
}
fun Planets.kmByDays(days: Double): Double
{
    ///SunRotation je doba obehu okolo slnka písaná v km (povedzme), čiže vzdialenosť / 365 = vzdialenostť ktorú ubehne
    ///za náš pozemský rok * počet dní = kolko km "zabehne" planéta za daný počet našich pozemských dní
    return (sunRotation / 365) * days
}
///--------------------------------------------------------------------------------------------------------------------------
interface HashTable<K, V>{
    fun insert(key : K, value : V)
    fun remove(key : K)
    fun contains(key : K) : Boolean
    fun pop(key : K) : V?
}

private class MyHash<K, V> constructor(hash_len : Int) : HashTable<K, V>
{
    private val hashLength = hash_len
    private var Data = Array(hashLength) { mutableListOf<V>() }

    private fun generateHash(key: K): Int
    {
        val intKey: Int = key.hashCode()
        return intKey % hashLength
    }

    override fun insert(key : K, value : V)
    {
        Data[generateHash(key)].add(value)
    }

    override fun remove(key : K)
    {
        if (contains(key))
        {
            val index = Data[generateHash(key)].lastIndex
            Data[generateHash(key)].removeAt(index)
        }
    }

    override fun contains(key : K) : Boolean
    {
        val hashKey : Int = generateHash(key)
        return Data[hashKey].lastIndex != -1
    }

    override fun pop(key : K) : V?
    {
        val hashKey = generateHash(key)
        return if (contains(key))
        {
            Data[hashKey].last()
        }
        else null
    }
}

fun main()
{
    println(switchLowerAndUpperCases("tHE eXAMPLE oF tHE tEXT."))


    val myHashTable = MyHash<String, Int> (3)
    myHashTable.insert("Name_1", 18)
    println(myHashTable.pop("Name_1"))

    myHashTable.insert("Name_2", 58)
    println(myHashTable.pop("Name_2"))

}
