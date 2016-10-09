import gulp from "gulp";
import runSequence from "run-sequence";
import del from "del";
import plumber from "gulp-plumber";
import sourceMaps from "gulp-sourcemaps";
import concat from "gulp-concat";
import uglify from "gulp-uglify";
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
gulp.task("build:app:src", ["build:app:src:index", "build:app:src:js"]);
gulp.task("watch:app:src", ["watch:app:src:index", "watch:app:src:js"]);

gulp.task("build:vendor", ["build:vendor:js", "build:vendor:css"]);
gulp.task("watch:vendor", ["watch:vendor:js", "watch:vendor:css"]);
gulp.task("build:vendor:js", ["build:vendor:js:bower", "build:vendor:js:npm"]);
gulp.task("watch:vendor:js", ["watch:vendor:js:bower", "watch:vendor:js:npm"]);
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
 * app:src:js
 */
gulp.task("build:app:src:js", () => {
    return gulp
        .src(config.files.js)
        .pipe(plumber())
        .pipe(sourceMaps.init())
        .pipe(concat("app.js"))
        .pipe(uglify())
        .pipe(rename({suffix: ".min"}))
        .pipe(sourceMaps.write("./"))
        .pipe(gulp.dest(config.dirs.distSrcApp));
});

gulp.task("watch:app:src:js", () => {
    gulp.watch(config.files.js, ["build:app:src:js"]);
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
        .src([])
        .pipe(plumber())
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
