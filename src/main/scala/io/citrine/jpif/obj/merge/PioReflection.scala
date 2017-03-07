package io.citrine.jpif.obj.merge

import java.lang.reflect.Method

import io.citrine.jpif.obj.common.Pio

import scala.collection.JavaConversions._

/**
  * @author Sean Paradiso
  */
class PioReflection(instance: Pio) {

  private def getAllMethods(clazz: Class[_], pattern: String): Map[String, Method] = {
    val fields: Map[String, Method] = clazz.getDeclaredMethods
      .map(f => (f.getName, f))
      .filter(_._1.matches(pattern))
      .toMap

    if (clazz.getSuperclass != null) {
      fields ++ getAllMethods(clazz.getSuperclass, pattern)
    } else {
      fields
    }
  }

  def getMethods(names: java.util.List[String]): java.util.List[Method] = {
    names.map(k => getters.getOrElse(k, setters(k)))
  }

  def getMethod(name: String): Method = {
    getters.getOrElse(name, setters(name))
  }

  def getGetterFieldKeys(): java.util.List[String] = {
    getters.keys.toList.sorted
  }

  lazy val getters: Map[String, Method] = getAllMethods(instance.getClass, "get.*")
    .filter(_._2.getParameterCount == 0)
  lazy val setters: Map[String, Method] = getAllMethods(instance.getClass, "set.*")
    .filter(_._2.getParameterCount == 1)

  getters.values.foreach(_.setAccessible(true))
  setters.values.foreach(_.setAccessible(true))

  lazy val listGetters: Seq[Method] = getters.filter(v => isList(v._2)).map(_._2).toList
  lazy val pioGetters: Seq[Method] = getters.filterNot(v => isList(v._2)).map(_._2).toList

  def isList(method: String): Boolean = {
    getters(method).getReturnType.getCanonicalName == "java.util.List"
  }

  def isList(method: Method): Boolean = {
    method.getReturnType.getCanonicalName == "java.util.List"
  }
}
