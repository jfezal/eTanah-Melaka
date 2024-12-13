<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<%-- <script type="text/javascript"
       src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>--%>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/styles/styles.css" />
    </head>

    <body>
        <script type="text/javascript">
            $(document).ready(function () {

                $('#tmbh_hakmilik').hide();
                var len = $(".daerah").length;

                for (var i = 0; i <= len; i++) {
                    $('.idHakmilikBaru' + i).click(function () {
                        window.open("${pageContext.request.contextPath}/hakmilik?hakmilikDetail&idHakmilik=" + $(this).text(), "eTanah",
                                "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
                    });
                }
            });

            function tmbhHakmilik() {
                $('#tmbh_hakmilik').show();
            }

            function edithakmilik(id, id2) {
                window.open("${pageContext.request.contextPath}/daftar/betul_hakmilik?editHakmilikPermohonan&idHakmilik=" + id + "&idPermohonan=" + id2, "eTanah",
                        "status=0,toolbar=0,location=0,menubar=0,width=900,height=400");
            }

            function deleteHakmilik(val, val2, f, val3) {
                form = document.form1;
                var answer = confirm("adakah anda pasti untuk Hapus?");
                if (answer) {
                    form.action = "${pageContext.request.contextPath}/daftar/betul_hakmilik?deleteHakmilik&idHakmilik=" + val + "&idPermohonan=" + val2 + "&idMh=" + val3;
                    form.submit();
                }
            }

            function popupTambahHakmilik(id) {
                var url = '${pageContext.request.contextPath}/daftar/betul_hakmilik?showCarianHakmilik&idPermohonan=' + id;
                window.open(url, "eTanah", "status=1,toolbar=0,location=1,menubar=0,width=900,height=300");
            }

            function saveHakmilik(val, f) {
                if ($('#idHakmilikBaru').val() == "") {
                    alert('Sila Masukkan ID Hakmilik');
                } else {
                    f.action = f.action + '?simpanHakmilik&idPermohonan=' + val;
                    f.submit();
                }
            }

            function reload(v)
            {
                var frm = document.form1;
                var url = "${pageContext.request.contextPath}/daftar/betul_hakmilik?checkPermohonan&idPermohonan=" + v;
                frm.action = url;
                frm.submit();

            }

            function generate(v)
            {
                var frm = document.form1;
                var url = "${pageContext.request.contextPath}/daftar/betul_hakmilik?generateAkuanPenerimaan&idPermohonan=" + v;
                frm.action = url;
                frm.submit();

            }
            function generateresit(v)
            {
                var frm = document.form1;
                var url = "${pageContext.request.contextPath}/daftar/betul_hakmilik?generateResitBayaran&idPermohonan=" + v;
                frm.action = url;
                frm.submit();

            }
            function KemaskiniPenyerah(id) {
                window.open("${pageContext.request.contextPath}/daftar/betul_hakmilik?popapKemaskini&idMohon=" + id, "eTanah",
                        "status=0,toolbar=0,location=0,menubar=0,width=900,height=400");
            }



            function kemaskiniWakilKuasa(id, idWakil) {

                window.open("${pageContext.request.contextPath}/daftar/betul_hakmilik?popapKemaskiniWakilKuasa&idMohon=" + id + "&idWakil=" + idWakil, "eTanah",
                        "status=0,toolbar=0,location=0,menubar=0,width=900,height=400");
            }

            function buang(id, idWakil, idDokumen)
            {
                form = document.form1;
                var answer = confirm("adakah anda pasti untuk Hapus?");
                if (answer) {
                    form.action = "${pageContext.request.contextPath}/daftar/betul_hakmilik?deletewakilkuasa&idMohon=" + id + "&idWakil=" + idWakil + "&idDokumen=" + idDokumen;
                    form.submit();
                }

            }

            function TambahWakilKuasa(id) {
                window.open("${pageContext.request.contextPath}/daftar/betul_hakmilik?popapWakilKuasa&idMohon=" + id, "eTanah",
                        "status=0,toolbar=0,location=0,menubar=0,width=900,height=400");
            }




        </script>
        <s:messages />
        <s:errors />

        <div id="page_div">

            <s:form beanclass="etanah.view.daftar.utilitiBawaPihak" name="form1">
                <s:hidden name="permohonan.idPermohonan"></s:hidden>
                    <fieldset class="aras1">

                        <legend>Baiki Perserahan Awalan1</legend>
                        <br>
                        <p><label for="idPermohonan">Id Permohonan/ID Perserahan :</label>
                        <s:text name="idPermohonan" id="idPermohonan" onkeyup="this.value=this.value.toUpperCase();" />
                    </p>

                    <p>
                        <label>&nbsp;</label>
                        <s:submit name="cariPermohonan" value="Seterusnya" class="btn" />
                    </p>
                    <br>
                </fieldset>
                <br>
                <c:if test="${actionBean.hakmilikPermohonanKemaskini ne null}">
                    <s:hidden name="permohonan.idPermohonan"></s:hidden>
                    ${actionBean.permohonan.idPermohonan} 
                    <label for="idHakmilikLama">Id Hakmilik Lama:</label>
                    <s:text name="idHakmilikLama" id="idHakmilikLama" onkeyup="this.value=this.value.toUpperCase();" />
                    <s:submit name="janaPihak" value="janaPihak" class="btn" />
                </div>
            </c:if>
        </s:form>
    </body>
</html>