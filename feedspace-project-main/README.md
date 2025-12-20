# FeedSpace Project
# Вернись в корень
cd /c/Users/Admin/java/feedspase-project

# Пересобери с новым плагином
mvn clean install -DskipTests

# Запусти с новой конфигурацией
mvn -pl feedspace-application spring-boot:run -Dspring.profiles.active=dev