# Kafka ile Tek Topic ve İki Consumer Group Kullanarak Veri Yönetimi

Bu doküman, Kafka'da tek bir topic kullanarak iki farklı consumer group ile veri yönetimini nasıl gerçekleştirebileceğinizi özetler. 

## Proje Amacı

Veri işleme ve mikroservis mimarilerinde genellikle her iş süreci için ayrı topic'ler oluşturulur. Ancak, bazı senaryolarda verimliliği artırmak ve kaynakları daha iyi yönetmek için tek bir topic üzerinden iki farklı `consumer group` kullanmak daha etkili olabilir. Bu yaklaşım:

- Kaynak kullanımını optimize eder.
- Yönetimsel karmaşıklığı azaltır.
- Performansı artırır.

## Mimari ve Akış

### 1. Tek Topic Kullanımı

İki farklı topic oluşturmak yerine, tek bir Kafka topic'i kullanarak veri akışını yönetiriz. Bu, yönetim ve yapılandırma maliyetlerini azaltır.

### 2. İki Consumer Group Kullanımı

İki farklı `consumer group` ile aynı topic'ten veri alırız. Bu sayede, verileri paralel olarak işleyebiliriz:

- **Consumer Group 1**: `actionA` türündeki olayları işler.
- **Consumer Group 2**: `actionB` türündeki olayları işler.

### 3. Veritabanından Local Cache'e Veri Alma

Uygulama başlatıldığında, gerekli parametreleri veritabanından alır ve bir local cache'e yükleriz. Bu, Kafka'dan gelen verilerle hızlı bir şekilde çalışmamızı sağlar.

### 4. Event İşleme

Kafka'dan gelen her bir event'in `action` anahtarına göre, local cache'deki parametreleri günceller ve veritabanına kaydederiz.

## Adım Adım Rehber

1. **Tablo Oluşturma**: Veritabanında gerekli tabloları oluşturun.
2. **Veri Yükleme**: Uygulama başladığında veritabanından gerekli verileri local cache'e alın.
3. **Kafka Konfigürasyonu**: Tek bir topic oluşturun ve iki farklı consumer group ile yapılandırın.
4. **Event İşleme**: Kafka'dan gelen olayları dinleyin ve `action` anahtarına göre local cache ve veritabanını güncelleyin.
