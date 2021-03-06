require ${BPN}.inc

SRC_URI += "file://${BPN}.initd"

SRC_URI_append_libc-musl = " file://Drop-use-of-error-h.patch"

inherit update-rc.d

INITSCRIPT_NAME = "${BPN}"

do_compile() {
    oe_runmake nbcat
    oe_runmake mkemlog
}

do_install() {
   install -Dm 0755 ${WORKDIR}/${BPN}.initd ${D}${sysconfdir}/init.d/${BPN}
   install -Dm 0755 ${S}/nbcat ${D}${bindir}/nbcat
   install -Dm 0755 ${S}/mkemlog ${D}${bindir}/mkemlog
}

RDEPENDS_${PN} += "kernel-module-emlog"
