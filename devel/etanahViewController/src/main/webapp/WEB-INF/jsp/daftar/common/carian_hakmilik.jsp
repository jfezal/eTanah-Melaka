<%--
    Document   : carian_hakmilik
    Created on : 03 November 2009, 4:40:06 PM
    Author     : khairil
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<html>
    <head>
        <title>Carian Hakmilik</title>
        <link type="text/css" href="${pageContext.request.contextPath}/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css" rel="stylesheet" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.simplemodal-1.3.3.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-ui-1.8.custom.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah.dialog.hakmilik.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
         <script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
        <link type="text/css" rel="stylesheet"
              href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>

        <script type="text/javascript">
            function save(event, f){
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                $.post(url,q,
                function(data){
                    if(data == '1')
                    {
                        alert('Sila masukan data pada label yang bertanda *. ');
                    }else{
                        //alert($('#perincianHakmilik',opener.document).html());
                        $('#page_div',opener.document).html(data);
                        self.close();
                    }

                },'html');

            }
            $(document).ready(function() {
                dialogInit('Carian Hakmilik');
                // $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});

            });

            <%--function save2(){
                self.opener.addTable
            }--%>


        </script>
    </head>
    <body>
        <s:errors/>
        <s:messages/>
        <div class="subtitle">
            <s:form beanclass="etanah.view.stripes.common.MaklumatHakmilikPermohonanActionBean">
                <s:hidden name="idHakmilik" />
                <fieldset class="aras1">
                    <legend>
                        Carian Hakmilik
                    </legend>

                    <p>

                        <label for="ID Hakmilik">ID Hakmilik :</label>
                        <s:text name="idHakmilikBaru" id="idHakmilikBaru" onclick="dialog(this.id,'${pageContext.request.contextPath}/hakmilik?carianHakmilikIndependent');"/>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <s:submit name="searchHakmilik" value="Cari" class="btn"/>
                    </p>
                </fieldset>
            </div>
            <br>

            <div class="subtitle">

                <c:if test="${actionBean.hb.idHakmilik != null}">
                    <%--<s:hidden name="sejarahHakmilik.idHakmilik"/>--%>
                    <fieldset class="aras1">
                        <legend>Hakmilik</legend>
                        <p><label>ID Hakmilik : </label>
                            ${actionBean.hb.idHakmilik}&nbsp;
                        </p>

                        <p><label>&nbsp;</label>
                            <s:button name="simpanHakmilik" id="simpan" value="Simpan" class="btn" onclick="save(this.name,this.form);"/>
                        </p>
                    </fieldset>

                </c:if>
            </s:form>
        </div>
    </body>
</html>