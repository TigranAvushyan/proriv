package com.application.proriv.domain.fake;

import com.application.proriv.domain.model.Product;

import java.util.List;

/**
 * @author - Tigran Avushyan <tigran.avushyan@gmail.com>
 * @created - 22/08/2021
 * @project - proriv
 */


public class FakeProducts {
  public static List<Product> getProducts() {
    return List.of(
        Product.builder().name("Пирожок с капустой").price(165).build(),
        Product.builder().name("Пирожок с картофелем").price(75).build(),
        Product.builder().name("Пирожок с яйцом и зеленью").price(165).build(),
        Product.builder().name("Пирожок с ветчиной и сыром").price(135).build(),
        Product.builder().name("Рогалик с джемом").price(105).build(),
        Product.builder().name("Котлета в тесте").price(15).build(),
        Product.builder().name("Сосиска в тесте").price(225).build(),
        Product.builder().name("Пицца с грибами и курицей").price(75).build(),
        Product.builder().name("Пицца с колбасой").price(225).build(),
        Product.builder().name("Самса с курицей").price(135).build(),
        Product.builder().name("Хачапури").price(225).build(),
        Product.builder().name("Лепешка с луком").price(105).build(),
        Product.builder().name("Венгерка творожная").price(195).build(),
        Product.builder().name("Ватрушка с творогом").price(195).build(),
        Product.builder().name("Ватрушка с изюмом").price(75).build(),
        Product.builder().name("Слойка с курицей").price(225).build(),
        Product.builder().name("Круасаны  со сгущенкой").price(75).build(),
        Product.builder().name("Круасаны  с шоколадом").price(165).build(),
        Product.builder().name("Сочник").price(225).build(),
        Product.builder().name("Булочка с изюмом").price(45).build(),
        Product.builder().name("Булочка с маком").price(15).build(),
        Product.builder().name("Плюшка с сахаром").price(195).build(),
        Product.builder().name("Пирожок с капустой жар.").price(195).build(),
        Product.builder().name("Пирожок с кортофелем жар.").price(15).build(),
        Product.builder().name("Пирожок с повидлой жар.").price(105).build(),
        Product.builder().name("Чебуреки").price(135).build(),
        Product.builder().name("Беляши").price(135).build()
    );
  }
}
