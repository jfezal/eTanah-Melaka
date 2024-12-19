<%-- 
    Document   : dev_maklumat_pemohon_plot
    Created on : Apr 30, 2013, 2:57:50 PM
    Author     : rajib
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript">
     $(document).ready(function() {
         
         
            
         
      });
</script>
<s:form beanclass="etanah.view.stripes.pembangunan.MaklumatPemohonPlotActionBean">
    <s:messages />
    <s:errors />
    <div class="subtitle">
        <c:if test="${fn:length(actionBean.pemohonListIndividu) gt 0}">
        
        <fieldset class="aras1">
            <legend>
                Senarai Pemohon Individu
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.pemohonListIndividu}" cellpadding="0" cellspacing="0" id="line">

                    <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}
                        <%--s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/--%>
                    </display:column>
                    <display:column property="pihak.nama" title="Nama" style="vertical-align:baseline"/>
                    <display:column title="Syer" style="vertical-align:baseline"> 
                                ${line.syerPembilang}/${line.syerPenyebut}
                    </display:column>
                    <display:column property="pihak.jenisPengenalan.nama" title="Jenis Pengenalan" style="vertical-align:baseline"/>
                    <display:column property="pihak.noPengenalan" title="No. KP/Syarikat" style="vertical-align:baseline"/>
                    <%--display:column property="pihak.bangsa.nama" title="Bangsa" style="vertical-align:baseline"/--%>
                    <display:column title="Alamat" style="vertical-align:baseline">
                        ${line.pihak.alamat1}
                        <c:if test="${line.pihak.alamat2 ne null}"> , </c:if>
                        ${line.pihak.alamat2}
                        <c:if test="${line.pihak.alamat3 ne null}"> , </c:if>
                        ${line.pihak.alamat3}
                        <c:if test="${line.pihak.alamat4 ne null}"> , </c:if>
                        ${line.pihak.alamat4}
                    </display:column>
                    <display:column property="pihak.poskod" title="Poskod" style="vertical-align:baseline"/>
                    <display:column property="pihak.negeri.nama" title="Negeri" style="vertical-align:baseline"/>
                    <c:if test="${edit}">
                        <display:column title="Bahagian yang Dipohon">
                            <div align="center">
                              <s:text value="${line.jumlahPembilang}" name="syerPembilangIndividu[${line_rowNum-1}]" size="5" id="pembilangI_${line_rowNum-1}"/> /
                              <s:text value="${line.jumlahPenyebut}" name="syerPenyebutIndividu[${line_rowNum-1}]" size="5" id="penyebutI_${line_rowNum-1}"/>
                            </div>
                        </display:column>
                        <display:column title="Plot Dipohon" >
                            <s:select value="${line.dalamanNilai1}" style="text-transform:uppercase" name="plotTerpilihIndividu[${line_rowNum-1}]" id="individu_${line_rowNum-1}">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${actionBean.listPlot}" label="noPlot" value="noPlot" />
                        </s:select>

                        </display:column>
                        
                        
                        <%--display:column property="dalamanNilai1" title="Pilih Plot" style="vertical-align:baseline">
                                <div align="center">
                                    <s:select name="noPlot" id="noPlot" value="${line.dalamanNilai1}">
                                            <s:option value="">--Sila Pilih--</s:option>
                                            <s:options-collection collection="${actionBean.listPlot}" label="noPlot" value="noPlot"/>
                                        </s:select>
                                </div>
                        </display:column--%>
                    </c:if>
                    <c:if test="${!edit}">
                        <display:column title="Bahagian yang Dipohon" style="vertical-align:baseline">
                            ${line.jumlahPembilang} / ${line.jumlahPenyebut}
                        </display:column>
                        <display:column property="dalamanNilai1" title="Plot Dipohon" style="vertical-align:baseline"/>
                    </c:if>
                </display:table>
            </div>

        </fieldset>
    </c:if>
    <c:if test="${fn:length(actionBean.pemohonListCompany) gt 0}">
        <fieldset class="aras1">
            <legend>
                Senarai Pemohon Syarikat
            </legend>
            <div class="content" align="center">
                <display:table class="tablecloth" name="${actionBean.pemohonListCompany}" cellpadding="0" cellspacing="0" id="line">

                    <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}
                        <%--s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/--%>
                    </display:column>
                    <display:column property="pihak.nama" title="Nama" style="vertical-align:baseline"/>
                    <display:column title="Syer" style="vertical-align:baseline"> 
                                ${line.syerPembilang}/${line.syerPenyebut}
                    </display:column>
                    <display:column property="pihak.jenisPengenalan.nama" title="Jenis Pengenalan" style="vertical-align:baseline"/>
                    <display:column property="pihak.noPengenalan" title="No. KP/Syarikat" style="vertical-align:baseline"/>                    
                    <display:column title="Alamat" style="vertical-align:baseline">
                        ${line.pihak.alamat1}
                        <c:if test="${line.pihak.alamat2 ne null}"> , </c:if>
                        ${line.pihak.alamat2}
                        <c:if test="${line.pihak.alamat3 ne null}"> , </c:if>
                        ${line.pihak.alamat3}
                        <c:if test="${line.pihak.alamat4 ne null}"> , </c:if>
                        ${line.pihak.alamat4}
                    </display:column>
                    <display:column property="pihak.poskod" title="Poskod" style="vertical-align:baseline"/>
                    <display:column property="pihak.negeri.nama" title="Negeri" style="vertical-align:baseline"/>
                    <display:column title="Ahli Lembaga Pengarah" style="vertical-align:baseline">
                        <c:set value="1" var="count"/>
                        <c:forEach items="${line.pihak.senaraiPengarah}" var="pengarah">
                            <c:if test="${pengarah.nama ne null}">
                                <c:out value="${count}"/>) &nbsp;
                                <c:out value="${pengarah.nama}"/><br>
                            </c:if>
                            <c:if test="${pengarah.nama eq null}">
                                <c:out value=""/>- &nbsp;
                            </c:if>
                            <c:set value="${count + 1}" var="count"/>
                        </c:forEach>
                    </display:column>
                    <display:column property="pihak.modalBerbayar" title="Modal Berbayar" style="vertical-align:baseline"/>
                    <display:column property="pihak.modalDibenar" title="Modal Dibenarkan" style="vertical-align:baseline"/>
                    <display:column title="Tarikh Daftar Syarikat">
                        <fmt:formatDate value="${line.pihak.tarikhLahir}" pattern="dd/MM/yyyy"/>
                    </display:column>
                    <c:if test="${edit}">
                        <display:column title="Bahagian yang Dipohon">
                            <div align="center">
                              <s:text value="${line.jumlahPembilang}" name="syerPembilangCompany[${line_rowNum-1}]" size="5" id="pembilangC_${line_rowNum-1}"/> /
                              <s:text value="${line.jumlahPenyebut}" name="syerPenyebutCompany[${line_rowNum-1}]" size="5" id="penyebutC_${line_rowNum-1}"/>
                            </div>
                        </display:column>
                        <display:column title="Plot Dipohon" >
                            <s:select value="${line.dalamanNilai1}" style="text-transform:uppercase" name="plotTerpilihCompany[${line_rowNum-1}]" id="kompeni_${line_rowNum-1}">
                            <s:option value="">--Sila Pilih--</s:option>
                            <s:options-collection collection="${actionBean.listPlot}" label="noPlot" value="noPlot" />
                        </s:select>

                        </display:column>
                        
                        <%--display:column property="dalamanNilai1" title="Pilih Plot" style="vertical-align:baseline">
                                <div align="center">
                                    <s:select name="noPlot" id="noPlot" value="${line.dalamanNilai1}">
                                            <s:option value="">--Sila Pilih--</s:option>
                                            <s:options-collection collection="${actionBean.listPlot}" label="noPlot" value="noPlot"/>
                                        </s:select>
                                </div>
                        </display:column--%>
                    </c:if>
                    <c:if test="${!edit}">
                        <display:column title="Bahagian yang Dipohon" style="vertical-align:baseline">
                            ${line.jumlahPembilang} / ${line.jumlahPenyebut}
                        </display:column>
                        <display:column property="dalamanNilai1" title="Plot Dipohon" style="vertical-align:baseline"/>
                    </c:if>
                </display:table>
            </div>
        </fieldset>
    </c:if>
    <c:if test="${edit}">
            <p>
                <label>&nbsp;</label>
                <s:button class="btn" value="Simpan" name="simpanPemohonPlot" id="simpan" onclick="doSubmit(this.form, this.name, 'page_div')"/>&nbsp;
            </p>
        </c:if>
    </div>
    
</s:form>
