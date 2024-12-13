<%-- 
    Document   : carianSyaratNyata
    Created on : Jun 3, 2011, 12:42:22 PM
    Author     : NageswaraRao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


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

            function masuk(){
                var index=document.getElementById('index').value;
                opener.document.getElementById('kod'+index).value = $("#selectedKod").val();
                <%--alert(opener.document.getElementById('kod'+index).value);--%>
                opener.document.getElementById('syaratNyata'+index).value = $("#selectedKod").val() +" - "+  $("#selectedNama").val();
                <%--alert(opener.document.getElementById('syaratNyata'+index).value);--%>
                self.close();
            }

            function selectRadio(obj){
                document.getElementById("selectedKod").value=obj.id;
                document.getElementById("selectedNama").value=obj.value;

            }
        </script>

        <s:errors/>
        <s:messages/>
        <s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
        <div class="subtitle">
           <s:form beanclass="etanah.view.stripes.pembangunan.CarianSyaratSekatanActionBean">
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
                        <label>Kategori Tanah :</label>
                        <s:select style="text-transform:uppercase" name="kodKatTanah">
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${list.senaraiKodKategoriTanah}" label="nama" value="kod" />
                        </s:select>
                    </p>
                     <p>
                        <label>Syarat Nyata :</label>
                        <s:text name="syaratNyata" id="syaratNyata2"/>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <s:submit name="cariKodSyaratNyata2" value="Cari" class="btn"/>
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
                        <display:table style="width:100%" class="tablecloth" name="${actionBean.listKodSyaratNyata}" pagesize="10" cellpadding="0" cellspacing="0" requestURI="/pembangunan/carianSyaratSekatan" id="line">
                            <display:column> <s:radio name="radio_" id="${line.kod}" value="${line.syarat}"   onclick="javascript:selectRadio(this)"/></display:column>
                            <display:column title="Kod Syarat Nyata" property="kod"/>
                            <display:column title="Nama Kod Syarat Nyata" property="syarat"/>
                            </display:table>
                    </p>
                    <c:if test="${fn:length(actionBean.listKodSyaratNyata) > 0}" >
                        <p><label>&nbsp;</label>
                            <s:button name="simpanKodSyaratNyata" value="Simpan" id="simpanKodSyaratNyata" class="btn" onclick="javascript:masuk();"/>
                        </p>
                    </c:if>
                </fieldset>

            </s:form>
        </div>

