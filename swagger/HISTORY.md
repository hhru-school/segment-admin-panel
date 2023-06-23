## v0.01

### layers

GET /layers Получить весь спосок слоёв

GET /layers/{layerId} Информацию о слое, вместе со всеми родителями.

## v.0.02

GET /layers/changes/{layerId} Получить изменения слоя

## v.0.03

### entrypoints

GET /entrypoints/layer/{layerId} Получить "Точки Входа" слоя

GET /entrypoints/{entrypointId}/layer/{layerId} Получить состояния вопросов/полей по "Точке Входа" слоя

## v.0.03

### entrypoints
GET /layers/{layerId}/changes  Получить изменения слоя

GET /layers/{layerId}/merge Сделать слой стабильным, объединить с родительским