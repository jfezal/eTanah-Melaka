<%--
    Document   :  search_Page
    Created on :  Jul 19, 2010, 4:44:13 PM
    Author     :  Siti Fariza Hanim @ some fix by @mr5rule
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


            function test(){
                var index=document.getElementById('index').value;
                opener.document.getElementById('kod'+index).value = $("#selectedKod").val();
                opener.document.getElementById('namaJabatan'+index).value = $("#selectedNama").val();
                self.close();
                
            }

            function test2(){
                var index=document.getElementById('index').value;
                opener.document.getElementById('salinanKod'+index).value = $("#selectedKod").val();
                opener.document.getElementById('salinanKepada'+index).value = $("#selectedNama").val();
                self.close();

            }


            function closeWindow() {
                //uncomment to open a new window and close this parent window without warning
                //var newwin=window.open("popUp.htm",'popup','');
                if(navigator.appName=="Microsoft Internet Explorer") {
                    this.focus();self.close(); }
                else { window.open('','eTanah',''); window.close(); }
            }


            function uppercase(){
            <%-- alert("test");--%>
                    var kodAgensiNama = document.getElementById("kodAgensiNama").value;
                    document.getElementById("kodAgensiNama").value = kodAgensiNama.toUpperCase();
                }

                function selectRadio(obj){
                    document.getElementById("selectedKod").value=obj.id;
                    document.getElementById("selectedNama").value=obj.value;
            
                }





                $(document).ready(function() {
                    maximizeWindow();
                    $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
            <%--$("#simpanKodAgensi").click(function(){
                alert($("#selectedKod").val());
               alert($("#selectedNama").val());
                
            // opener.document.getElementById('namaJabatan['+parseInt(${index})+']').value = $('input:radio[name=radio_]:checked').val();
             self.close();
            });--%>
                });

        </script>
    </head>
    <body>
        <s:errors/>
        <s:messages/>
        <div class="subtitle">
            <s:form beanclass="etanah.view.stripes.pengambilan.JabatanTeknikal2ActionBean">
                <%-- <s:hidden name="idHakmilik" />--%>
                <fieldset class="aras1">
                    <legend>
                        Kod Agensi
                    </legend>
                    <s:hidden id="selectedKod" name="selectedKod" />
                    <s:hidden id="selectedNama" name="selectedNama" />
                    <p>
                        <s:hidden name="index" id="index" />
                    </p>
                    <p>
                        <label>Kod :</label>
                        <s:text name="kod" id="kod"/>
                    </p>
                    <p>
                        <label>Nama Jabatan :</label>
                        <s:text name="kodAgensiNama" id="kodAgensiNama"  onkeyup="javascript:uppercase();"/>
                    </p>
                    <c:if test="${agensi eq true}">

                        <p><label>&nbsp;</label>
                            <s:submit name="cariKodAgensi" value="Cari" class="btn"/>
                            <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:window.close();"/>
                        </p>
                    </c:if>
                    <c:if test="${salinan eq true}">
                        <p><label>&nbsp;</label>
                            <s:submit name="cariKodAgensi2" value="Cari" class="btn"/>
                            <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:window.close();"/>
                        </p>
                    </c:if>
                </fieldset>
            </div>
            <br>

            <div class="subtitle">

                <%-- <c:if test="${actionBean.kodSyaratNyata != null}">--%>

                <fieldset class="aras1">
                    <legend></legend>
                    <p>
                        <display:table style="width:100%" class="tablecloth" name="${actionBean.listKodAgensi}" pagesize="15" cellpadding="0" cellspacing="0" requestURI="/pelupusan/jabatan_teknikal12" id="line">
                            <display:column> <s:radio name="radio_" id="${line.kod}" value="${line.nama}"   onclick="javascript:selectRadio(this)"/></display:column>
                            <display:column title="Kod" property="kod"/>
                            <display:column title="Nama Kod Agensi" property="nama" style="text-transform:uppercase;"/>
                            <display:column title="Kategori Agensi" property="kategoriAgensi.nama" style="text-transform:uppercase;"/>
                        </display:table>
                    </p>
                    <c:if test="${fn:length(actionBean.listKodAgensi) > 0}" >
                        <c:if test="${agensi eq true}">
                            <p><label>&nbsp;</label>
                                <s:button name="simpanKodAgensi" value="Simpan" id="simpanKodAgensi" class="btn" onclick="javascript:test();"/>
                            </p>
                        </c:if>
                        <c:if test="${salinan eq true}">
                            <p><label>&nbsp;</label>
                                <s:button name="simpanSalinan" value="Simpan" class="btn" onclick="javascript:test2();"/>
                            </p>
                        </c:if>
                    </c:if>
                </fieldset>

            </s:form>
        </div>
    </body>
</html>
