<%--
    Document   : popUpKemasukanPenerimaWakil
    Created on : Jul 19, 2010, 7:24:45 PM
    Author     : 5rule
--%>

<%--
    Document   : popup_kodsyaratnyata
    Created on : Dec 28, 2009, 7:34:09 PM
    Author     : mohd.fairul
--%>

<html>
    <head>
        <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
        <%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kemasukan Berkaitan Hakmilik</title>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
        <script type="text/javascript"
        src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
        <script src="<%= request.getContextPath()%>/js/ui.datepicker-ms.js" type="text/javascript"></script>
        <s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>

        <script type="text/javascript">

            $(document).ready( function(){


            <%--set focus--%>
                    $('input').focus(function() {
                        $(this).addClass("focus");
                    });

                    $('input').blur(function() {
                        $(this).removeClass("focus");
                    });

                    $('select').focus(function() {
                        $(this).addClass("focus");
                    });

                    $('select').blur(function() {
                        $(this).removeClass("focus");
                    });
                });

function simpanPenerima(f)
{    
    <%--alert('${actionBean.jPihak}');--%>
            <%--alert("${actionBean.hakmilikPihakBerkepentingan.hakmilik.idHakmilik}");--%>
            var url = '${pageContext.request.contextPath}/daftar/suratkuasawakil?savePopup&idPihakPemberi=${actionBean.pihak.idPihak}&hakmilikWakil=${actionBean.hakmilik.idHakmilik}&jPihak=${actionBean.jPihak}';
            <%--window.open(url);--%>

                var q = $(f).formSerialize();
                $.post(url,q,
                           function(data){
                               $('#page_div',opener.document).html(data);
                           <%--alert(data);--%>
                           },'html');
                           setTimeout("window.self.close()",1000);
}

        </script>
    </head>
    <body>
        <s:form beanclass="etanah.view.stripes.nota.NotaDaftarActionBean">

            <s:messages/>
            <s:errors/>
            <div class="subtitle">
              <%--  <fieldset class="aras1">
                    <legend>
                        Maklumat Pemberi
                    </legend>
                    <p>
                        <label>Nama Pemberi :</label>${actionBean.pihak.nama}
                    </p>
                    <p>
                        <label>No Pengenalan :</label>${actionBean.pihak.noPengenalan}
                    </p>
                </fieldset>--%>
                <fieldset class="aras1">
                    <legend>
                        Maklumat Penerima
                    </legend>
                    <p>
                        <label>Nama Penerima :</label>${actionBean.pihak.nama}
                        <s:hidden name="namaPenerima" value="${actionBean.pihak.nama}" />
                    </p>
                    <p>
                        <s:hidden name="jPihak" value="${actionBean.jPihak}" />
                        <label>No. Pengenalan : </label><%--<s:select name="jenisPengenalanPenerima" id="jenisPengenalanPenerima" style="width:100pt;" value="${actionBean.pihak.noPengenalan}">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod"/>
                        </s:select> &nbsp; <s:text name="noPengenalanPenerima" id="noPengenalanPenerima" style="width:140pt;"/>--%>
                        ${actionBean.pihak.jenisPengenalan.nama}&nbsp;&nbsp;&nbsp;${actionBean.pihak.noPengenalan}
                        <s:hidden name="jenisPengenalanPenerima" value="${actionBean.pihak.jenisPengenalan.kod}"/>
                        <s:hidden name="noPengenalanPenerima" value="${actionBean.pihak.noPengenalan}"/>
                    </p>
                    <br/>

                </fieldset>

                    <br/>
                      <fieldset class="aras1">
                    <legend>
                        Maklumat Tambahan
                    </legend>
                    <p>
                        <label>Amaun Maksima (RM):</label><s:text name="amaunMaksima" style="width:250pt;"/>
                    </p>
                    <p>
                        <label>Catatan / Syarat : </label>
                        <s:textarea name="syaratTambahan" style="width:250pt; height:300pt"></s:textarea>
                    </p>
                    <br/>
                    <br/>
                </fieldset>
                    <div align="center">
                    <s:button class="btn" onclick="simpanPenerima(this.form)" name="simpan" value="Simpan"/>&nbsp;
                    <s:button class="btn" onclick="javascript:window.close();" name="simpan" value="Tutup"/>
                </div>

            </div>
        </s:form>


    </body>
</html>
