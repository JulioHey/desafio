enum Brand {
  toyota,
  ford,
  bmw,
  honda,
  chevrolet,
  mercedes,
  audi,
  tesla,
  volkswagen,
  nissan,
}

extension BrandExtension on Brand {
  String toJson() => name.toUpperCase();

  static Brand fromJson(String value) {
    try {
      return Brand.values.firstWhere(
        (e) => e.name.toUpperCase() == value.toUpperCase(),
      );
    } catch (e) {
      throw Exception();
    }
  }
}
