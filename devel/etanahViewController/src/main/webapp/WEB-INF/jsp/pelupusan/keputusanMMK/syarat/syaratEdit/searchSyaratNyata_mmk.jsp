<%--
    Document   : searchSyaratNyata_lptn
    Created on : May 10, 2011
    Author     : Shazwan
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Carian Syarat Nyata</title>
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
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>

<script type="text/javascript">
            $(document).ready(function() {
            $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'})
        });
    function save(event, f){
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.close();
        },'html');
    }
        function uppercase(){
    <%-- alert("test");--%>
            var syaratNyata2 = document.getElementById("syaratNyata2").value;
            document.getElementById("syaratNyata2").value = syaratNyata2.toUpperCase();
        }

        function selectRadio(obj){

            document.getElementById("kod").value=obj.id;
            document.getElementById("syaratNyata").value=obj.value;
            alert(obj.value);
        }

</script>
<body>
    <script>
        // Allow the user to be warned by default.
        var allowPrompt = true;
                
        //window.onbeforeunload = WarnUser;
        function WarnUser()
        {   
            if(allowPrompt)
                refreshpage();
            if(allowPrompt)
            {
                event.returnValue = "Segala Maklumat tidak akan disimpan, anda pasti?";
            }
            else
            {
                // Reset the flag to its default value.
                allowPrompt = true;
            }
        }
        function NoPrompt()
        {
            allowPrompt = true;
        }

    </script>
    <s:errors/>
    <s:messages/>
    <div class="subtitle">
        <s:form beanclass="etanah.view.stripes.pelupusan.RekodKeputusanMMKV2ActionBean">
            <fieldset class="aras1">
                <legend>
                    Carian Syarat Nyata
                </legend>
                <s:hidden id="kod" name="disSyaratSekatan.kod" />
                <s:hidden id="kodSekatan" name="disSyaratSekatan.kodSktn" />
                            <s:hidden name="idHakmilik" id="idHakmilik"/>
                <p>
                    <s:hidden name="index" id="index" />
                </p>
                <p>

                    <label>Kod Syarat Nyata :</label>
                    <s:text name="disSyaratSekatan.kodSyaratNyata" id="kodSyaratNyata"/>
                </p>
                <p>

                    <label>Syarat Nyata :</label>
                    <s:text name="disSyaratSekatan.syaratNyata2" id="syaratNyata2"/>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <s:submit name="cariKodSyaratNyata" value="Cari" class="btn" onclick="NoPrompt();"/>
                </p>
            </fieldset>
        </div>
        <br>

        <div class="subtitle">

            <%-- <c:if test="${actionBean.kodSyaratNyata != null}">--%>

            <fieldset class="aras1">
                <legend></legend>
                <p>
                    <display:table style="width:100%" class="tablecloth" name="${actionBean.disSyaratSekatan.listKodSyaratNyata}" pagesize="10" cellpadding="0" cellspacing="0" requestURI="/pelupusan/laporan_tanahV2" id="line">
                        <display:column> <s:radio name="radio_" id="${line.kod}" value="${line.syarat}" onclick="javascript:selectRadio(this);"/></display:column>
                        <display:column title="Kod Syarat Nyata" property="kod"/>
                        <display:column title="Nama Kod Syarat Nyata" property="syarat"/>
                    </display:table>
                </p>
                <c:if test="${fn:length(actionBean.disSyaratSekatan.listKodSyaratNyata) > 0}" >
                    <p><label>&nbsp;</label>
                         <s:submit name="simpanKodSyaratNyata" value="Simpan" class="btn" onclick="NoPrompt();"/>
                        <%--<s:button name="simpanKodSyaratNyata" value="Simpan" id="simpanKodSyaratNyata" class="btn" onclick="javascript:masuk();"/>--%>
                    </p>
                </c:if>
            </fieldset>

        </s:form>
    </div>
</body>


