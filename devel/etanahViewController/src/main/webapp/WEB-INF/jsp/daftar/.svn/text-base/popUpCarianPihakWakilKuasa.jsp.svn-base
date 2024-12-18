<%--
    Document   : popUpCarianPihak
    Created on : Jul 19, 2010, 7:24:45 PM
    Author     : 5rule, fix by Aizuddin
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
        <style type="text/css">
            .replaceP{
                width:100%;
                color:#003194;
                font-weight: bold;
            }

            .L
            {

                color:#003194;
                font-weight: bold;
            }

            .T{
                width:40%
            }


        </style>
        <script type="text/javascript">
            function save(event, f){
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                $.post(url,q,
                function(data){
                    $('#page_div',opener.document).html(data);
                    self.close();
                },'html');
            }
            
            function PilihSW(event, f){
var idpihak = $('input:radio[name=chkboxpihak]:checked').val();
                var q = $(f).formSerialize();
                var url = f.action + '?' + event +"&idPihak="+idpihak;
               
            window.open(url, "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=750");

            }
        </script>
    </head>
    <body>
        <s:form beanclass="etanah.view.daftar.SuratWakilKuasaActionBean">
            <s:hidden name="kodSerah" id="kodSerah" value="${actionBean.mohon.kodUrusan.kodPerserahan.kod}"/>

            <s:messages/>
            <s:errors/>

            <s:hidden name="jPihak" value="${actionBean.jPihak}"/>
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Kemasukan Pemberi
                    </legend>
                    <div class="subtitle">
                        <div class="content" align="center">
                            <table class="tablecloth" width="50%">
                                <tr><th colspan="2">Ruang Carian</th></tr>
                                <tr><td class="T"><p class="L" align="right">Nama :&nbsp;</p></td><td><s:text name="nama" id="namaPemberi" style="width:100%"/></td></tr>
                                <tr><td colspan="2"><p class="L" align="center" style="color:red">Atau</p></td></tr>
                                <tr><td><p class="L" align="right">Jenis Pengenalan :&nbsp;</p></td><td> <s:select name="kodKp" id="" value="kod" style="width:100%">
                                            <s:option value="null">Pilih ...</s:option>
                                            <s:options-collection collection="${list.senaraiKodJenisPengenalan}" label="nama" value="kod" />
                                        </s:select></td></tr>
                                <tr><td><p class="L" align="right"> No Pengenalan :&nbsp;</p></td><td> <s:text name="noKp" id="noKp" style="width:100%"/></td></tr>
                            </table>
                        </div>
                        <br/>
                        <div align="center">
                            <s:submit name="cariPihak" value="Cari" class="btn"/>
                            <s:button class="btn" onclick="javascript:window.close();" name="tutup" value="Tutup"/>
                        </div>
                    </div>
                    <br/>



                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.pihakList}" pagesize="10" cellpadding="0" cellspacing="0"
                                       requestURI="${pageContext.request.contextPath}/daftar/suratwakilkuasa?cariPemberi" id="line">
                            <display:column title="Pilih">
                                <s:radio name="chkboxpihak" id="chkbox${line_rowNum}" value="${line.idPihak}" class="checkbox"/>
                            </display:column>
                            <display:column title="No" sortable="true">${line_rowNum}</display:column>
                            <%--<display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>--%>
                            <display:column property="nama" title="Nama"/>
                            <display:column property="noPengenalan" title="No Pengenalan"/>
                            <display:column property="jenisPengenalan.kod" title="Jenis Pengenalan"/>
                            <%--<display:column property="jenis.nama" title="Jenis Pihak Berkepentingan"/>--%>

                        </display:table>
                    </div>
                    <c:if test="${!empty actionBean.pihakList}">
                        <div align="center">

                            <c:if test="${actionBean.jPihak eq 'penerima'}">

                                <c:if test="${actionBean.notSW}">
                                    <s:button name="simpanByPihak" value="Pilih" class="btn" onclick="save(this.name, this.form);"/>
                                </c:if>
                                <c:if test="${!actionBean.notSW}">
                                    <s:submit name="updateSyaratPopup" value="Pilih" class="btn"/>
                                </c:if>
                            </c:if>
                            <c:if test="${actionBean.jPihak eq 'pemberi'}">
                                <s:button name="simpanByPihak" value="Pilih" class="btn" onclick="save(this.name, this.form);"/>
                            </c:if>

                        </div>
                    </c:if>
                </fieldset>
            </div>
        </s:form>
    </body>
</html>
