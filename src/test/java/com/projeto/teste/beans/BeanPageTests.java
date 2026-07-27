package com.projeto.teste.beans;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.projeto.teste.projetotestes.beans.BeanPage;

class BeanPageTests {

  @Test
  void shouldCreateBean() {
    BeanPage bp = new BeanPage();
    bp.setContent("conteudo");
    bp.setTitle("titulo do bean");
    assertNotNull(bp);
    assertEquals("conteudo", bp.getContent());
    assertEquals("titulo do bean", bp.getTitle());
  }
}
