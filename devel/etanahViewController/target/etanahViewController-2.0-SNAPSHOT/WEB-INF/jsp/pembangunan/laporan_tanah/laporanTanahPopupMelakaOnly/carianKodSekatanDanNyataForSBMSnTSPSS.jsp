<%-- 
    Document   : laporan_tanahV2CarianKodSekatanDanNyata_sbms_tspss
    Created on : Apr 17, 2014, 5:22:12 PM
    Author     : khairul.hazwan
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
        opener.document.getElementById('syaratNyata'+index).value = $("#selectedKod").val() +" - "+  $("#selectedNama").val();
        self.close();
    }

    function selectRadio(obj){
        document.getElementById("selectedKod").value=obj.id;
        document.getElementById("selectedNama").value=obj.value;

    }
            
    function selectRadio1(obj){
        document.getElementById("selectedKod1").value=obj.id;
        document.getElementById("selectedNama1").value=obj.value;

    }
        
</script>

<s:errors/>
<s:messages/>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<div class="subtitle">
    <s:form beanclass="etanah.view.stripes.pembangunan.LaporanTanahMelakaActionBean">
        <fieldset class="aras1">
            <c:if test = "${actionBean.forEdit eq 'NY'}">
                <legend>
                    Carian Syarat Nyata
                </legend>
                <s:hidden id="selectedKod" name="selectedKod" />
                <s:hidden id="selectedNama" name="selectedNama" />
                <s:hidden id="idPlot" name="idPlot" />
                <s:hidden id="forEdit" name="forEdit" />

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
                    <s:submit name="cariKodSyaratNyataSBMSnTSPSS" value="Cari" class="btn" />
                    <s:button name="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                </p>
            </c:if>

            <c:if test = "${actionBean.forEdit eq 'SK'}">
                <legend>Carian Sekatan</legend>
                <s:hidden id="selectedKod1" name="selectedKod1" />
                <s:hidden id="selectedNama1" name="selectedNama1" />
                <s:hidden id="idPlot" name="idPlot" />
                <s:hidden id="forEdit" name="forEdit" />

                <div class="subtitle">

                    <p>
                        <label>Kod Sekatan</label>
                        <s:text name="kodSekatan" id="kodSekatan"/>
                    </p>
                    <p>
                        <label>Nama Sekatan Kepentingan</label>
                        <s:text name="sekatan" id="sekatan"/>
                    </p>
                    <p>
                        <label>&nbsp;</label>
                        <s:submit name="cariKodSekatanSBMSnTSPSS" value="Cari" class="btn"/>
                        <s:button name="tutup" value="Tutup" class="btn" onclick="self.close()"/>
                    </p>
                </div>
            </c:if>
        </fieldset>
    </div>
    <br>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            <c:if test = "${actionBean.forEdit eq 'NY'}">           
                <p>
                    <display:table style="width:100%" class="tablecloth" name="${actionBean.listKodSyaratNyata}" pagesize="10" cellpadding="0" cellspacing="0" requestURI="/pembangunan/melaka/laporanTanah" id="line">
                        <display:column> <s:radio name="radio_" id="${line.kod}" value="${line.syarat}" onclick="javascript:selectRadio(this)"/></display:column> <%--onclick="javascript:selectRadio(this)"--%>
                        <display:column title="Kod Syarat Nyata" property="kod"/>
                        <display:column title="Nama Kod Syarat Nyata" property="syarat"/>
                    </display:table>
                </p>
            </c:if>

            <c:if test = "${actionBean.forEdit eq 'SK'}">   
                <p>
                    <display:table style="width:100%" class="tablecloth" name="${actionBean.listKodSekatan}" pagesize="10" cellpadding="0" cellspacing="0" requestURI="/pembangunan/melaka/laporanTanah" id="line">
                        <display:column> <s:radio name="radio_" id="${line.kod}" value="${line.sekatan}"   onclick="javascript:selectRadio1(this)"/></display:column>
                        <display:column title="Kod Sekatan" property="kod"/>
                        <display:column title="Nama Sekatan" property="sekatan"/>
                    </display:table>
                </p>
            </c:if>


            <div align="center">
                <c:if test="${fn:length(actionBean.listKodSekatan) > 0 || fn:length(actionBean.listKodSyaratNyata) > 0}">
                    <s:submit name="simpanKodSyaratNyataSBMSnTSPSS" value="Simpan" id="simpanKodSyaratNyata" class="btn"/>
                </c:if>
                <s:button name="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </div>
        </fieldset>
    </div>
</s:form>



