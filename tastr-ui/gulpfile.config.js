export let dirs = {
    src: "src",
    srcApp: "src/app",
    srcImg: "src/content/images",
    dist: "target/dist",
    distSrc: "target/dist/src",
    distSrcApp: "target/dist/src/app",
    distSrcImg: "target/dist/src/content/images",
    distSrcVendorJs: "target/dist/src/vendor/js",
    distSrcVendorCss: "target/dist/src/vendor/css"
};

export let files = {
    distSrc: `${dirs.distSrc}/**/*`,
    index: `${dirs.src}/index.html`,
    js: `${dirs.srcApp}/**/*.js`,
    img: `${dirs.srcImg}/**/*`,
    bower: "bower.json",
    npm: "package.json"
};
