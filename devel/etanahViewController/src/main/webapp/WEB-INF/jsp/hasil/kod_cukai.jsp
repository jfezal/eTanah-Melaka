<%-- 
    Document   : kod_cukai
    Created on : Aug 18, 2014, 12:19:38 AM
    Author     : haqqiem
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN"
    "http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript">
        $(document).ready(function() {
            var l = ${fn:length(actionBean.senaraiKadarCukai)};
            if(l>0){
                $("#carian").show();}
            else
                $("#carian").hide();
        });
   
    function clearText1(id) {
        $("#" + id +" select").each( function(el) {
            $(this).val('');
        });
    }
    
    function refreshRekod(){
        $("#cari").click();
    }
    
    function edit(id){
        window.open("${pageContext.request.contextPath}/hasil/kod_cukai?editKadar&kod="+id, "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1280,height=800");
    }
    
    function add(){
        window.open("${pageContext.request.contextPath}/hasil/kod_cukai?addKadar", "eTanah",
                "status=0,scrollbars=1,toolbar=0,location=0,menubar=0,width=1280,height=800");
    }
</script>

<div id="jab">
    <div align="center">
        <table style="width:99.2%" bgcolor="green">
            <tr><td width="100%" height="20" ><div style="background-color: green;" align="center">
                        <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">Kod Kadar Cukai</font>
                    </div></td></tr>
        </table></div>
        <stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
        <stripes:errors />
        <stripes:form beanclass="etanah.view.stripes.hasil.KodCukaiActionBean" id="kod_cukai">
            <stripes:text name="kodNegeri" id="negeri" style="display:none;" />
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Carian</legend>
                <p>
                    <label>Kod Kegunaan Tanah :</label>
                    <stripes:select name="kodGunaTanah" id="gunaTanah">
                        <stripes:option label="Sila Pilih..." value="" />
                        <c:forEach items="${listUtil.senaraiKegunaanTanah}" var="i" >
                            <stripes:option value="${i.kod}">${i.kod} - ${i.nama}</stripes:option>
                        </c:forEach>
                    </stripes:select>
                </p>
                <p>
                    <label>Bandar/Pekan/Mukim :</label>
                    <stripes:select name="kodBpm" id="bpm">
                        <stripes:option label="Sila Pilih ..."  value="" />
                        <stripes:options-collection collection="${listUtil.senaraiKodBandarMukim}" label="nama" value="kod" sort="nama"/>
                    </stripes:select>
                </p>
                <p>
                    <label>&nbsp;</label>
                    <stripes:submit name="Step2" value="Cari" class="btn" id="cari"/>
                    <stripes:button name=" " value="Isi Semula" class="btn" onclick="clearText1('kod_cukai');"/>
                    <stripes:button name="" value="Tambah" class="btn" onclick="add();"/>
                </p>
                <br>
            </fieldset>
        </div>
        <div class="subtitle" id="carian">
            <fieldset class="aras1">
                <legend>Hasil Carian</legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.senaraiKadarCukai}" requestURI="/hasil/kod_cukai" id="line" pagesize="10">
                        <display:column title="Bil" style="text-align:center">${line_rowNum}.</display:column>
                        <display:column title="Bandar/Pekan/Mukim">
                            ${line.bandarPekanMukim.kod} - ${line.bandarPekanMukim.nama}
                        </display:column>
                        <display:column title="Kegunaan Tanah">
                            ${line.kegunaanTanah.kod} - ${line.kegunaanTanah.nama}
                        </display:column>
                        <display:column title="Kod Syarat Nyata" property="syaratNyata.syarat"/>
                        <display:column title="Kadar Meter <sup>2</sup>" style="text-align:right">
                            <fmt:formatNumber value="${line.kadarMeterPersegi}" pattern="#,##0.000"/>
                        </display:column>
                        <display:column title="Minima (RM)" style="text-align:right">
                            <fmt:formatNumber value="${line.kadarMinimum}" pattern="#,###,##0.00"/>
                        </display:column>
                        <%--display:column title="Luas" style="text-align:right">
                            <fmt:formatNumber value="${line.luas}" pattern="#,###,##0.00"/>
                        </display:column--%>
                        <display:column title="Aktif">
                            ${line.aktif == 'Y' ? 'Ya' : 'Tidak'}
                        </display:column>
                        <display:column title="Tindakan" style="text-align:center">
                            <img alt='Klik Untuk Hapus' src='${pageContext.request.contextPath}/images/edit.gif' onclick="edit('${line.kod}');" onmouseover="this.style.cursor='pointer';"/>
                        </display:column>
                    </display:table>
                </div>
            </fieldset>
        </div>
    </stripes:form>
</div>