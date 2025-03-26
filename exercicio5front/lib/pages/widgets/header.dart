import 'package:flutter/material.dart';
import 'package:get/get.dart';

class Header extends StatelessWidget {
  const Header({super.key});

  Widget navigationButtons({
    required String route,
    required String text,
  }) {
    return Container(
        child: GestureDetector(
            onTap: () => {Get.offNamed(route)},
            child: Text(
              text,
              style: const TextStyle(fontSize: 24, color: Colors.black),
            )));
  }

  @override
  Widget build(BuildContext context) {
    return Container(
        width: context.width,
        height: 100,
        decoration: const BoxDecoration(
          color: Colors.blue,
        ),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: [
            navigationButtons(route: '/home', text: 'Home'),
            navigationButtons(route: '/by-brand', text: 'Brand'),
            navigationButtons(route: '/by-decade', text: 'Decade'),
          ],
        ));
  }
}
