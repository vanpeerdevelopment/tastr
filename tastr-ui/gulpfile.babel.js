import gulp from "gulp";
import runSequence from "run-sequence";
import del from "del";
import plumber from "gulp-plumber";
import sourceMaps from "gulp-sourcemaps";
import concat from "gulp-concat";
import uglify from "gulp-uglify";
import typescript from "gulp-typescript";
import rename from "gulp-rename";
import mainBowerFiles from "main-bower-files";
import cssnano from "gulp-cssnano";
import browserSync from "browser-sync";
import httpProxy from "http-proxy-middleware";
import * as config from "./gulpfile.config";

const browserSyncServer = browserSync.create("tastr");

/*
 * main tasks
 */
gulp.task("default", ["build"]);

gulp.task("build", callback => {
    runSequence(
        "clean",
        ["build:app", "build:vendor"],
        callback
    );
});

gulp.task("dev", callback => {
    runSequence(
        "clean",
        ["build:app", "build:vendor"],
        ["watch:app", "watch:vendor", "serve"],
        callback);
});

/*
 * helper tasks
 */
gulp.task("build:app", ["build:app:src"]);
gulp.task("watch:app", ["watch:app:src"]);
gulp.task("build:app:src", ["build:app:src:index", "build:app:src:systemjsconfig", "build:app:src:ts", "build:app:src:img"]);
gulp.task("watch:app:src", ["watch:app:src:index", "watch:app:src:systemjsconfig", "watch:app:src:ts", "watch:app:src:img"]);

gulp.task("build:vendor", ["build:vendor:js"]);
gulp.task("watch:vendor", ["watch:vendor:js"]);
gulp.task("build:vendor:js", ["build:vendor:js:npm", "build:vendor:js:npm:folder"]);
gulp.task("watch:vendor:js", ["watch:vendor:js:npm", "watch:vendor:js:npm:folder"]);
gulp.task("build:vendor:css", ["build:vendor:css:bower"]);
gulp.task("watch:vendor:css", ["watch:vendor:css:bower"]);

/*
 * general
 */
gulp.task("clean", () => {
    return del(config.dirs.dist);
});

gulp.task("serve", callback => {
    let backendProxy = httpProxy(
        "/api",
        {target: "http://localhost:8282"});
    browserSyncServer.init(
        {
            server: {
                baseDir: config.dirs.distSrc,
                middleware: [backendProxy]
            },
            port: 8080,
            ui: {port: 8081},
            files: config.files.distSrc
        },
        callback
    );
});

/*
 * app:src:index
 */
gulp.task("build:app:src:index", () => {
    return gulp
        .src(config.files.index)
        .pipe(gulp.dest(config.dirs.distSrc));
});

gulp.task("watch:app:src:index", () => {
    gulp.watch(config.files.index, ["build:app:src:index"]);
});

/*
 * app:src:systemjsconfig
 */
gulp.task("build:app:src:systemjsconfig", () => {
    return gulp
        .src(config.files.systemjsconfig)
        .pipe(gulp.dest(config.dirs.distSrc));
});

gulp.task("watch:app:src:systemjsconfig", () => {
    gulp.watch(config.files.systemjsconfig, ["build:app:src:systemjsconfig"]);
});

/*
 * app:src:js
 */
gulp.task("build:app:src:ts", () => {
    let tastrTypescript = typescript.createProject('tsconfig.json');
    let tsResult = tastrTypescript.src().pipe(tastrTypescript());
    return tsResult.js
        // .pipe(plumber())
        // .pipe(sourceMaps.init())
        // .pipe(concat("app.js"))
        // .pipe(uglify())
        // .pipe(rename({suffix: ".min"}))
        // .pipe(sourceMaps.write("./"))
        .pipe(gulp.dest(config.dirs.distSrcApp));
});

gulp.task("watch:app:src:ts", () => {
    gulp.watch(config.files.ts, ["build:app:src:ts"]);
});

/*
 * app:src:img
 */
gulp.task("build:app:src:img", () => {
    return gulp
        .src(config.files.img)
        .pipe(gulp.dest(config.dirs.distSrcImg));
});

gulp.task("watch:app:src:img", () => {
    gulp.watch(config.files.img, ["build:app:src:img"]);
});

/*
 * vendor:js:bower
 */
let srcJsToJs = path => {
    if (path.basename.endsWith(".src")) {
        path.basename = path.basename.substring(0, path.basename.lastIndexOf(".src"));
    }
};

gulp.task("build:vendor:js:bower", () => {
    return gulp
        .src(mainBowerFiles({
            checkExistence: true,
            filter: "**/*.js"
        }))
        .pipe(plumber())
        .pipe(rename(srcJsToJs))
        .pipe(sourceMaps.init())
        .pipe(uglify())
        .pipe(rename({suffix: ".min"}))
        .pipe(sourceMaps.write("./"))
        .pipe(gulp.dest(config.dirs.distSrcVendorJs));
});

gulp.task("watch:vendor:js:bower", () => {
    gulp.watch(config.files.bower, ["build:vendor:js:bower"]);
});

/*
 * vendor:js:npm
 */
gulp.task("build:vendor:js:npm", () => {
    return gulp
        .src([
            "node_modules/core-js/client/shim.js",
            "node_modules/zone.js/dist/zone.js",
            "node_modules/reflect-metadata/Reflect.js",
            "node_modules/systemjs/dist/system.src.js",
            "node_modules/@angular/core/bundles/core.umd.js",
            "node_modules/@angular/common/bundles/common.umd.js",
            "node_modules/@angular/compiler/bundles/compiler.umd.js",
            "node_modules/@angular/platform-browser/bundles/platform-browser.umd.js",
            "node_modules/@angular/platform-browser-dynamic/bundles/platform-browser-dynamic.umd.js",
            "node_modules/@angular/http/bundles/http.umd.js",
            "node_modules/@angular/router/bundles/router.umd.js",
            "node_modules/@angular/forms/bundles/forms.umd.js",
        ])
        .pipe(plumber())
        .pipe(rename(srcJsToJs))
        .pipe(sourceMaps.init())
        .pipe(uglify())
        .pipe(rename({suffix: ".min"}))
        .pipe(sourceMaps.write("./"))
        .pipe(gulp.dest(config.dirs.distSrcVendorJs));
});

gulp.task("watch:vendor:js:npm", () => {
    gulp.watch(config.files.npm, ["build:vendor:js:npm"]);
});

/*
 * vendor:js:npm:folder
 */
gulp.task("build:vendor:js:npm:folder", ["build:vendor:js:npm:folder:rxjs", "build:vendor:js:npm:folder:ng"]);
gulp.task("build:vendor:js:npm:folder:rxjs", () => {
    return gulp
        .src("node_modules/rxjs/**/*")
        .pipe(gulp.dest(`${config.dirs.distSrcVendorJs}/rxjs`));
});

gulp.task("build:vendor:js:npm:folder:ng", () => {
    return gulp
        .src("node_modules/angular-in-memory-web-api/**/*")
        .pipe(gulp.dest(`${config.dirs.distSrcVendorJs}/angular-in-memory-web-api`));
});

gulp.task("watch:vendor:js:npm:folder", () => {
    gulp.watch(config.files.npm, ["build:vendor:js:npm:folder"]);
});

/*
 * vendor:css:bower
 */
gulp.task("build:vendor:css:bower", () => {
    return gulp
        .src(mainBowerFiles({
            checkExistence: true,
            filter: "**/*.css"
        }))
        .pipe(plumber())
        .pipe(sourceMaps.init())
        .pipe(cssnano())
        .pipe(rename({suffix: ".min"}))
        .pipe(sourceMaps.write("./"))
        .pipe(gulp.dest(config.dirs.distSrcVendorCss));
});

gulp.task("watch:vendor:css:bower", () => {
    gulp.watch(config.files.bower, ["build:vendor:css:bower"]);
});
