export let dirs = {
    src: "src",
    srcApp: "src/app",
    dist: "target/dist",
    distSrc: "target/dist/src",
    distSrcApp: "target/dist/src/app",
    distSrcVendorJs: "target/dist/src/vendor/js",
    distSrcVendorCss: "target/dist/src/vendor/css"
};

export let files = {
    distSrc: `${dirs.distSrc}/**/*`,
    index: `${dirs.src}/index.html`,
    js: `${dirs.srcApp}/**/*.js`,
    bower: "bower.json",
    npm: "package.json"
};
