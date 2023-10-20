Projeyi çalıştırmak için docker-compose up yeterlidir. YEPZ…


Zookeeper nedir ne ise yarar ?        	Koordinasyon: Kafka çok sayıda broker'dan oluşur. Bu broker'lar arasındaki koordinasyonu sağlar. Örneğin yeni bir broker eklenmesi veya bir broker'ın düşmesi durumunda diğer broker'lara bildirimler Zookeeper tarafından yapılır.
* Yönetim: Kafka broker'larının konfigürasyon bilgileri, topic'lerin durumu gibi yönetimsel bilgiler Zookeeper'da saklanır.
* Servis Keşfi: Müşteriler hangi broker'lara bağlanacağını Zookeeper'dan öğrenir. Böylece broker'lar arasında yük dengesi sağlanmış olur.
* Oturum Yönetimi: Producer ve consumer'ların oturum bilgileri Zookeeper'da tutulur. Böylece oturumlar yönetilebilir.
* Sentez: Birden fazla Kafka çevrimi için merkezi bir nokta görevi görür. Tüm Kafka çevrimlerinin ortak konfigürasyon ve yönetim bilgileri burada tutulur.
  Kısaca Zookeeper, Kafka'nın tüm bileşenleri arasındaki senkronizasyonu, koordinasyonu ve yönetimini sağlayan önemli bir alt yapı servisidir. Kafka çalışması için Zookeeper'ın varlığı zorunludur.


Monolitik yapıda Kafka kullanımı bazı avantajlar sunsa da bazı dezavantajları da var.
Avantajları arasında:
* Yapının basit olması ve bakım/güncellemelerin kolay olması sayılabilir.
  Dezavantajları ise:
* Büyük monolitik yapıların yönetimi ve ölçeklendirilmesi zor olabilir.
* Hata ayıklama ve test etme zorluğu artar.
* Yeni özellik eklenmesi veya değişiklik yapılması monolitik yapının tümünü etkileyebilir.
  Genel olarak Kafka'nın mikroservis tabanlı mimariler ile daha iyi şekilde ölçeklendirilebileceği düşünülüyor. Ancak monolitik yapıda da kullanılabilir. Önemli olan sistemin ihtiyaçları, ölçeği ve değişim hızıdır. Ama Birbirinden bağımsız monolitik iki yapı düşünelim. Bunlardan biri Message-APP’imiz olsun bu  yapıların mono veya mikro ne olduğu önemsiz şekilde bunları Kafka ile haberleştirebiliriz.


Kafka genellikle aşağıdaki aşamalarda kullanılır:
* Veri Toplama: Sensörler, cihazlar ve uygulamalardan gelen verileri toplamak ve depolamak için kullanılır.
* Event Logging: Uygulama ve sistem olaylarını loglamak için kullanılır. Örneğin, kullanıcı etkileşimleri, hata raporları vb.
* Veri Akışı: Gerçek zamanlı veri akışı sağlamak için kullanılır. Örneğin, finans verileri, sensör verileri vb.
* Batch İşleme: Toplanan verileri işlemek, analiz etmek ve raporlamak için kullanılır.


Bir otel arama ve randevu sistemi için Kafka'yı şu şekilde kullanabilirsiniz:
* Rezervasyon işlemlerinin Kafka üzerinden diğer sistemlere (otel yönetim sistemi, muhasebe vb.) bildirilmesi.
* Randevu işlemlerinin Kafka üzerinden ilgili takvim sistemine, otel personeline iletilmesi.
* Müşteri işlemlerinin (iptal, değişiklik istekleri vb.) Kafka aracılığıyla ilgili servislere ulaştırılması.
* Otel odaları, randevu saatleri gibi durum değişikliklerinin Kafka ile anında senkronizasyonu.
* Müşteri şikayet, memnuniyet anketi gibi formların Kafka'ya yazılması, işlenmesi.
* Otel performans verilerinin, istatistiklerinin Kafka üzerinden toplanması, raporlanması.


Kafkanın en büyük artısı gecikmeyi azaltmaktır. Her bir  topicin partitionlarına tek bir uygulama bakması mantıklı olabilir mesela 6 partitionlu bi email topiği 6 proje ile dinlenirse Kafka rebalance yapıp her projeye 1 partition atar(Yatay Genişleme). Yani kısacası partition sayısı kadar consumer ayağa kaldırmak lazım. Fazlasını kaldırsan da işe yaramaz ama uygulaman hala yavaş ise  partition sayını arttırmalısın.

İdeal dünyada 1den fazla broker olur ve o brokerların kendi topicleri olur

Replication-factor = broker’a gelen  bilgiyi kaç brokerda saklayacağın dağıtacağın// minisi:

Veriye sonradan erişebiliyoruz, disk’e ekleniyor retention_ms 7 gün boyunca saklanır mesela

Partition calculator : https://eventsizer.io/


# spring-boot-kafka
