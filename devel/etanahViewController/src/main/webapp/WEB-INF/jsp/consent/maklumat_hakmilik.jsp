<%--
    Document   : MaklumatHakmilik
    Created on : Nov 20, 2009, 4:20:48 PM
    Author     : muhammad.amin
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript">          
          
    $(document).ready( function() {
        var len = $(".daerah").length;

        for (var i=0; i<=len; i++){
            $('.hakmilik'+i).click( function() {
                window.open("${pageContext.request.contextPath}/consent/maklumat_hakmilik?hakmilikPopup&idHakmilik="+$(this).text(), "eTanah",
                "status=0,toolbar=0,location=0,menubar=0,width="+screen.width+",height="+screen.height+",scrollbars=yes,left=0,top=0");
            });
        }
    });
</script>

<s:form beanclass="etanah.view.stripes.consent.MaklumatHakmilikActionBean">
    <s:messages/>
    <div class="subtitle displaytag" id="page_div">
        <fieldset class="aras1">
            <legend>
                Maklumat Hakmilik Permohonan
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0"
                               requestURI="/common/maklumat_hakmilik_permohonan" id="line" style="width:90%;">
                    <display:column title="Bil" sortable="true" style="vertical-align:top">${line_rowNum}</display:column>
                    <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}" style="vertical-align:top"/>
                    <c:if test="${line.hakmilik.noLot eq 'Tiada' || line.hakmilik.noLot  eq 'X'}">
                        <display:column property="hakmilik.noLot" title="Nombor Lot/PT" style="vertical-align:top"/>
                    </c:if>
                    <c:if test="${line.hakmilik.noLot ne 'Tiada' && line.hakmilik.noLot  ne 'X'}">
                        <display:column title="Nombor Lot/PT" style="vertical-align:top"><fmt:parseNumber value="${line.hakmilik.noLot}"/></display:column>
                    </c:if>
                    <display:column title="Luas"  style="vertical-align:top"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;<font style="text-transform:uppercase;">${line.hakmilik.kodUnitLuas.nama}</font></display:column>
                    <display:column title="Daerah" class="daerah" style="vertical-align:top"><font style="text-transform:uppercase;">${line.hakmilik.daerah.nama}</font></display:column>
                    <display:column title="Bandar/Pekan/Mukim" style="vertical-align:top"><font style="text-transform:uppercase;">${line.hakmilik.bandarPekanMukim.nama}</font></display:column>

                    <display:column title="Maklumat Atas Tanah"><s:textarea cols="50" name="hakmilikPermohonanList[${line_rowNum - 1}].hakmilik.maklumatAtasTanah"/></display:column>               
                    
                </display:table>
            </div>
            <p align="center">
                <s:button name="simpanHakmilik" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
            </p>
            <br/>
        </fieldset>
    </div>
    <c:if test="${fn:endsWith(actionBean.permohonan.kodUrusan.idWorkflow,'Negeri/Consent/MMK') ||
                  fn:endsWith(actionBean.permohonan.kodUrusan.idWorkflow,'Negeri/Consent/JKTL2') ||
                  fn:endsWith(actionBean.permohonan.kodUrusan.idWorkflow,'Negeri/Consent/pmwna') ||
                  fn:endsWith(actionBean.permohonan.kodUrusan.idWorkflow,'Consent2/Project1/RJT') ||
                  fn:endsWith(actionBean.permohonan.kodUrusan.idWorkflow,'Consent2/Project1/RJD') }">
          <br/>
          <div class="subtitle displaytag" id="page_div">
              <fieldset class="aras1">
                  <legend>
                      Maklumat Tambahan Hakmilik
                  </legend>
                  <div class="content" align="center">
                      <display:table class="tablecloth" name="${actionBean.laporanTanahList}" cellpadding="0" cellspacing="0"
                                     requestURI="/common/maklumat_hakmilik_permohonan" id="line" style="width:90%;">
                          <display:column title="Bil" sortable="true" style="vertical-align:top">${line_rowNum}</display:column>
                          <display:column property="hakmilikPermohonan.hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}" style="vertical-align:top"/>
                          <display:column title="Lokasi"><s:textarea cols="30" rows="4" name="laporanTanahList[${line_rowNum - 1}].rancanganKerajaan"/></display:column>
                          <display:column title="Alamat"><s:textarea cols="30" rows="4" name="laporanTanahList[${line_rowNum - 1}].usahaTanam"/></display:column>
                          <display:column title="Kategori/Jenis Premis"><s:textarea cols="30" rows="4" name="laporanTanahList[${line_rowNum - 1}].jenisBangunan"/></display:column>
                          <display:column title="Keadaan Muka Bumi"><s:textarea cols="30" rows="4" name="laporanTanahList[${line_rowNum - 1}].keadaanTanah"/></display:column>
                      </display:table>
                  </div>
                  <p align="center">
                      <s:button name="simpanLaporanTanah" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                  </p>
                  <br/>
              </fieldset>
          </div>
    </c:if>
</s:form>