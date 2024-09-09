FROM node:20.12-alpine AS build

WORKDIR /dist/src/app
COPY . .
RUN npm cache clean --force
RUN npm install
RUN npm run build

FROM nginx:1.26.0-alpine AS runtime

EXPOSE 80

VOLUME /tmp
# Clean /usr/share/nginx/html
RUN rm -rf /usr/share/nginx/html/*
COPY --from=build /dist/src/app/dist/todo-app/browser /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
