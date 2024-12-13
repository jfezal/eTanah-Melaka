<%-- 
    Document   : sej_dok_main
    Created on : Apr 22, 2014, 12:49:26 PM
    Author     : fikri
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carian Sejarah Hakmilik</title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.tools.min.js?select=full&debug=true"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
        <script type="text/javascript">
            function doSearch(e, f) {
                
                var b = $('#idHakmilik').val();
                if (b === '') {
                    alert('Sila masukan hakmilik.');
                    return;
                }

                f.action = f.action + '?' + e;
                f.submit();
            }
            
            function doViewReport(v) {
                var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
                window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
            }
        </script>

    </head>
    <body>
        <s:messages/>
        <s:errors/>
        <s:form beanclass="etanah.view.stripes.SejarahDokumenAction" name="form1">
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Carian Sejarah Hakmilik
                    </legend>           
                    <p>
                        <label>Hakmilik :</label>
                        <s:text name="idHakmilik" id="idHakmilik" onblur="toUppercase(this.id);"
                                onkeyup="this.value = this.value.toUpperCase();" size="30"
                                onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"/>
                    </p>
                    <br/>
                    <p>
                        <label>&nbsp;</label>
                        <s:button name="search" value="Cari" class="btn" onclick="doSearch(this.name, this.form);"/>
                        <s:button name="" value="Carian Semula" class="btn" onclick="carianSemula();"/>
                    </p>
                </fieldset>
            </div>
            <br/>

            <c:if test="${fn:length(actionBean.listSejarahDokumen) > 0}">
                <div class="subtitle">
                    <fieldset class="aras1">
                        <legend>
                            Keputusan Carian Dokumen
                        </legend>
                        <p class="instr">
                            Sila klik pada <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar" />
                            untuk semak dokumen.<br/>                           
                        </p>
                        <p align="center">
                            <label></label>
                            <display:table class="tablecloth" name="${actionBean.listSejarahDokumen}"
                                           cellpadding="0" cellspacing="0" id="line1">
                                <display:column title="Bil">${line1_rowNum}</display:column>
                                <display:column property="kodDokumen.kod" title="Kod Dokumen" group="1"/>
                                <display:column property="kodDokumen.nama" title="Nama Dokumen"/>
                                <display:column property="noVersi" title="Versi Cetakan"/>
                                <display:column title="Papar">                                    
                                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" title="Papar"
                                         onclick="doViewReport('${line1.idDokumen}');" height="20" width="20" alt="papar" onmouseover="this.style.cursor = 'pointer';"/>
                                </display:column>                                
                            </display:table>
                        </p>
                        <br/>
                    </fieldset>
                </div>                
            </c:if>
        </s:form>
    </body>
</html>
