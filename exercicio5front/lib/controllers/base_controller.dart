import 'package:flutter/material.dart';
import 'package:get/get.dart';

class BaseController extends GetxController {
  BaseController({this.context});

  final String? context;
}

abstract class BaseControllerStateful<T extends BaseController>
    extends StatefulWidget {
  BaseControllerStateful({super.key});
  final T controller = Get.find<T>();
}
