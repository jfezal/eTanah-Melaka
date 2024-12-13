<%-- 
    Document   : carian_kodsyaratnyata
    Created on : Apr 15, 2010, 10:46:02 AM
    Author     : khairil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carian Kod Syarat Nyata</title>
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
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <script src="<%= request.getContextPath()%>/pub/js/ui.datepicker-ms.js" type="text/javascript"></script>
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
            $(document).ready(function() {
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $("#simpanKodSyaratNyata").click(function(){
                    opener.document.getElementById('syaratNyata').value = $('input:radio[name=radio_]:checked').val();
                    self.close();
                });
            });
        </script>
    </head>
    <body>
        <s:errors/>
        <s:messages/>
        <s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
        <div class="subtitle">
            <s:form beanclass="etanah.view.daftar.utiliti.PertanyaanKod">
                <%-- <s:hidden name="idHakmilik" />--%>
                <fieldset class="aras1">
                    <legend>
                        Carian Kod Syarat Nyata
                    </legend>

                    <p>

                        <label>Kod Syarat Nyata :</label>
                        <s:text name="kodSyaratNyata" id="kodSyaratNyata"/>
                    </p>
                    <p>
                        <label>Kategori Tanah :</label>
                        <s:select style="text-transform:uppercase" name="kodKatTanah">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodKategoriTanah}" label="nama" value="kod" />
                        </s:select>
                    </p>

                    <p>

                        <label>Syarat Nyata :</label>
                        <s:text name="syaratNyata" id="syaratNyata"/>
                    </p>

                    <p>
                        <label>&nbsp;</label>
                        <s:submit name="cariKodSyaratNyata" value="Cari" class="btn"/>
                    </p>
                </fieldset>
            </div>
            <br>

            <div class="subtitle">

                <%-- <c:if test="${actionBean.kodSyaratNyata != null}">--%>

                <fieldset class="aras1">
                    <legend></legend>
                    <p>
                        <display:table style="width:100%" class="tablecloth" name="${actionBean.listKodSyaratNyata}" pagesize="15" cellpadding="0" cellspacing="0" requestURI="/utiliti/pertanyaan_kod" id="line">
                            <%-- <display:column> <s:radio name="radio_" id="radio_" value="${line.kod}"/></display:column>--%>
                            <display:column title="Kod" property="kodSyarat"/>
                            <display:column title="Syarat" property="syarat" />
                        </display:table>
                    </p>

                </fieldset>

                <%-- </c:if>--%>

            </s:form>
        </div>
    </body>
</html>
