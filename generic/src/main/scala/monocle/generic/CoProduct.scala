package monocle.generic

import monocle.SimplePrism
import monocle.function.SafeCast
import shapeless.Coproduct
import shapeless.ops.coproduct.{Selector, Inject}

import scalaz.Maybe


object coproduct extends CoProductInstances


trait CoProductInstances {
  
  implicit def coProductSafeCast[C <: Coproduct, A](implicit evInject: Inject[C, A], evSelector: Selector[C, A]): SafeCast[C, A] =
    new SafeCast[C, A] {
      def safeCast = SimplePrism[C, A](c => Maybe.optionMaybeIso.to(evSelector.apply(c)), evInject.apply)
    }
  

}