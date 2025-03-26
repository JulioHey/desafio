import 'package:exercicio5front/pages/widgets/header.dart';
import 'package:flutter/material.dart';

class BasePage extends StatelessWidget {
  final Widget content;

  const BasePage({super.key, required this.content});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        children: [
          const Header(),
          content,
        ],
      ),
    );
  }
}
