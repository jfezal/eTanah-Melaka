<%-- 
    Document   : kertas_ringkas_jktlm
    Created on : Jun 6, 2013, 2:04:22 PM
    Author     : muhammad.amin
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<style type="text/css">

    #uLabel {
        width: 15em;
        float: left;
        text-align: left;
        margin-right: 0px;
        display: block;
        color:#003194;
        font-weight: bold;
        font-family:Tahoma;
        font-size: 13px;
        margin-left: -3px;
    }
</style>

<script type="text/javascript">
    
    function reload(val) {
        doBlockUI();
        var url = '${pageContext.request.contextPath}/consent/kertas_ringkas_mmkn?selectHakmilik&idHakmilik=' + val;
        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function (xhr, ajaxOptions, thrownError){
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success : function(data){
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
    }
           
</script>

<s:form beanclass="etanah.view.stripes.consent.KertasRingkasJktlmActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Kertas Ringkas</legend>
            <table width="100%" border="1">
                <tr><td colspan="2"> <c:set value="1" var="count"/>
                        <c:forEach items="${actionBean.permohonan.senaraiUrusan}" var="senarai">       
                            <c:if test="${senarai.perihal eq 'TAJUK'}">
                                <font style="text-transform:uppercase;">  <c:out value="${senarai.catatan}"/></font> <br/>  
                            </c:if>
                            <c:set value="${count + 1}" var="count"/>
                        </c:forEach> </td></tr>
                <tr><td colspan="2"></td>&nbsp;</tr>
                <tr><td><b>Ringkasan Permohonan</b></td><td>
                        &nbsp;
                    </td>
                </tr>
                <tr><td>Pemohon</td><td>
                        <c:set value="1" var="count"/>
                        <c:forEach items="${actionBean.permohonan.senaraiPemohon}" var="senarai">       
                            <c:out value="${senarai.pihak.nama}"/> (<c:out value="${senarai.pihak.jenisPengenalan.nama}"/>  <c:out value="${senarai.pihak.noPengenalan}"/>)     
                            <c:set value="${count + 1}" var="count"/>
                        </c:forEach>&nbsp;</td></tr>
                <tr><td>Tarikh Permohonan</td><td>
                        <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.permohonan.infoAudit.tarikhMasuk}"/>
                    </td>
                </tr>

                <tr><td>Perihal Tanah</td><td>
                        <c:set value="1" var="count"/>
                        <c:forEach items="${actionBean.permohonan.senaraiHakmilik}" var="senarai">       
                            <c:out value="${senarai.hakmilik.bandarPekanMukim.nama}"/> Daerah <c:out value="${senarai.hakmilik.daerah.nama}"/> <br/>  
                            <c:out value="${senarai.hakmilik.kodHakmilik.kod}"/>&nbsp;<fmt:parseNumber value="${senarai.hakmilik.noHakmilik}"/>&nbsp;<c:out value="${senarai.hakmilik.lot.nama}"/>&nbsp;<fmt:parseNumber value="${senarai.hakmilik.noLot}"/><br>
                            <c:set value="${count + 1}" var="count"/>
                        </c:forEach>
                    </td>
                </tr>
                <tr><td>Keluasan</td><td>
                        <c:forEach items="${actionBean.permohonan.senaraiUrusan}" var="senarai">       
                            <c:if test="${senarai.perihal eq 'LUAS'}">
                                <c:out value="${senarai.catatan}"/> 
                            </c:if>
                        </c:forEach>
                    </td>
                </tr>
                <tr><td>Lokasi</td><td>
                        <s:textarea name="mohonUrusanLokasi.catatan" rows="4" style="width:97%;" class="normal_text"/>
                    </td>
                </tr>
                <tr><td>Keadaan Tanah</td><td>
                        <s:textarea name="mohonUrusanKeadaan.catatan" rows="4" style="width:97%;" class="normal_text"/>
                    </td>
                </tr>
                <tr><td>Pengesahan Status Pekerja</td><td>
                        <s:textarea name="mohonUrusanPengesahan.catatan" rows="4" style="width:97%;" class="normal_text"/>
                    </td>
                </tr>
                <tr><td>Ulasan Teknikal
                        <c:set value="1" var="count"/>
                        <c:forEach items="${actionBean.listUlasanTeknikal}" var="senarai">       
                            <br/><c:out value="${senarai.agensi.nama}"/>
                            <c:set value="${count + 1}" var="count"/>
                        </c:forEach>&nbsp;
                    </td>
                    <td>
                        <c:set value="1" var="count"/>
                        <c:forEach items="${actionBean.listUlasanTeknikal}" var="senarai">       
                            <br/> 
                            <s:radio name="listUlasanTeknikal[${count - 1}].diSokong" value="S"/>&nbsp;Sokong
                            <s:radio name="listUlasanTeknikal[${count - 1}].diSokong" value="T"/>&nbsp;Sokong Bersyarat
                            <s:radio name="listUlasanTeknikal[${count - 1}].diSokong" value="U"/>&nbsp;Tidak Sokong
                            <s:radio name="listUlasanTeknikal[${count - 1}].diSokong" value="V"/>&nbsp;Tiada Ulasan
                            <c:set value="${count + 1}" var="count"/>
                        </c:forEach>&nbsp;
                    </td>
                </tr>
                <tr><td>Ulasan PTG, Melaka</td><td>
                        <s:textarea name="mohonUrusanUlasan.catatan" rows="4" style="width:97%;" class="normal_text"/>
                    </td>
                </tr>

            </table>
            <p>
                <label>&nbsp;</label>
                <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>
        </fieldset>
    </div>
</s:form>
