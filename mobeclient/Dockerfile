FROM node:alpine AS builder

WORKDIR /app

COPY . .

RUN npm install && \
    npm run build

FROM nginx:alpine

EXPOSE 80
COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=builder /app/dist/mobeclient /usr/share/nginx/html/
