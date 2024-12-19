<%--
    Document   : searhSekatanNyataKpntngn.jsp
    Created on : August 08, 2011, 11:49:54 AM
    Author     : Shazwan
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
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

     function save(event, f){
                var q = $(f).formSerialize();
                var url = f.action + '?' + event;
                $.post(url,q,
                function(data){
                  $('#page_div',opener.document).html(data);
                   self.close();
                },'html');
           }

       function masuk(){
          // alert('t1');

            <%--var index=document.getElementById('index').value;--%>

             opener.document.getElementById('kodSktn').value = $("#selectedKod").val();
              opener.document.getElementById('syaratNyata1').value = $("#selectedNama").val();
            // opener.document.getElementById('syaratNyata1').value = $("#selectedKod").val()+ ' - '+ $("#selectedNama").val();
           //  alert('t2');
             self.close();


        }
        function masuk2(){
            <%--var index=document.getElementById('index').value;--%>
             opener.document.getElementById('kod').value = $("#selectedKod").val();
             opener.document.getElementById('syaratNyata').value = $("#selectedNama").val();
             self.close();
             opener.document.getElementById('syaratNyata').focus();
        }
        function uppercase(){
            <%-- alert("test");--%>
             var syaratNyata2 = document.getElementById("syaratNyata2").value;
             document.getElementById("syaratNyata2").value = syaratNyata2.toUpperCase();
        }

         function selectRadio(obj){

            document.getElementById("selectedKod").value=obj.id;
            document.getElementById("selectedNama").value=obj.value;

        }
</script>
<s:form beanclass="etanah.view.stripes.pelupusan.SekatanNyataKepentinganActionBean">
    <fieldset class="aras1">
                    <legend>
                        Carian Syarat Nyata
                    </legend>
                    <s:hidden id="selectedKod" name="selectedKod" />
                    <s:hidden id="selectedNama" name="selectedNama" />

                    <p>
                        <s:hidden name="index" id="index" />
                    </p>
                     <p>

                        <label>Kod Syarat Nyata :</label>
                        <s:text name="kodSyaratNyata" id="kodSyaratNyata"/>
                    </p>
                     <p>

                        <label>Syarat Nyata :</label>
                        <s:text name="syaratNyata2" id="syaratNyata2"/>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <s:submit name="cariKodSyaratNyata" value="Cari" class="btn"/>
                        <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:window.close();"/>
                    </p>
                </fieldset>
            </div>
            <br>

            <div class="subtitle">

               <%-- <c:if test="${actionBean.kodSyaratNyata != null}">--%>

                <fieldset class="aras1">
                    <legend></legend>
                    <p>
                        <display:table style="width:100%" class="tablecloth" name="${actionBean.listKodSyaratNyata}" pagesize="10" cellpadding="0" cellspacing="0" requestURI="/pelupusan/sekatanNyataKepentingan" id="line">
                            <display:column> <s:radio name="radio_" id="${line.kod}" value="${line.syarat}"   onclick="javascript:selectRadio(this)"/></display:column>
                            <display:column title="Kod Syarat Nyata" property="kod"/>
                            <display:column title="Nama Kod Syarat Nyata" property="syarat"/>
                            </display:table>
                    </p>
                    <c:if test="${fn:length(actionBean.listKodSyaratNyata) > 0}" >
                        <p><label>&nbsp;</label>
                            <s:button name="simpanKodSyaratNyata" value="Simpan" id="simpanKodSyaratNyata" class="btn" onclick="javascript:masuk2();"/>
                        </p>
                    </c:if>
                </fieldset>    
</s:form>