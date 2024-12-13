<%-- 
    Document   : maklumat_pemohonV2
    Created on : Jun 10, 2013, 3:49:36 PM
    Author     : afham
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>

<script type="text/javascript">
    function openFrame(type){
        doBlockUI();
        window.open("${pageContext.request.contextPath}/pelupusan/lelong_awam?openFrame&type="+type, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1000,height=500,scrollbars=yes");
    }
    
    function refreshV2Pemohon(type){
        var url = '${pageContext.request.contextPath}/pelupusan/lelong_awam?refreshpage&type='+type;
        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
        doUnBlockUI();
    }
    
    function showMaklumatLuarNegeri(idPemohon,idPihak,status)
    {
        window.open("${pageContext.request.contextPath}/pelupusan/lelong_awam?viewFormMaklumatTanahMilik&idPemohon="+idPemohon+"&idPihak="+idPihak+"&status="+status, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600,scrollbars=yes");
    }    
    function showKeluarga(idPemohon)
    {
        window.open("${pageContext.request.contextPath}/pelupusan/lelong_awam?viewFormMaklumatKeluarga&idPemohon="+idPemohon, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }   
    
    function showPengarah(idPemohon)
    {
        window.open("${pageContext.request.contextPath}/pelupusan/lelong_awam?viewFormMaklumatPengarah&idPemohon="+idPemohon, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=900,height=600");
    }  
    
    function simpankeputusan(val){
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pelupusan/lelong_awam?simpankeputusanbida&idPemohon='+val;
            $.get(url,
            function(data){
                $('#page_div').html(data);
                self.refreshV2Pemohon();
            },'html');
        }
    }
</script>

<s:form beanclass="etanah.view.stripes.pelupusan.LelongAwamActionBean">
    <s:errors/>
    <s:messages/>
    <s:hidden name="idHakmilik" id="idHakmilik"/>
    <s:hidden name="noPtSementara" id="noPtSementara"/>
    <div class="subtitle displaytag" align="center">
        <div class="subtitle">
            <fieldset class="aras1" id="locate">
                <legend>
                    Maklumat Pemohon
                    <span style="float:right">
                        <%--<a onclick="viewPerihalTanah();" onmouseover="this.style.cursor='pointer';" ><img alt="Papar" height="25px" width="25px" src='${pageContext.request.contextPath}/pub/images/icons/view_pelupusan_small.png'/></a> | --%>
                        <c:if test="${actionBean.stageId eq '20DaftarPembida'}">
                            <c:if test="${actionBean.disPemohonController.tPemohon}">
                            <a onclick="openFrame('tPemohon');" onmouseover="this.style.cursor='pointer';"><img alt="Kemaskini"  height="25px" width="25x" src='${pageContext.request.contextPath}/pub/images/icons/edit_pelupusan_small.png'/> </a>
                            </c:if>
                        </c:if>
                    </span>
                </legend>

                <c:if test="${fn:length(actionBean.senaraiKelompok) ne 0}">

                    <display:table class="tablecloth" name="${actionBean.senaraiKelompok}" cellpadding="0" cellspacing="0" id="line"
                                   requestURI="/pelupusan/lelong_awam">
                        <display:column title="Bil" sortable="true">${line_rowNum}
                            <s:hidden name="x" class="x${line_rowNum -1}" value="${line.idMohonKelompok}"/>
                        </display:column>
                        <display:column title="Id Permohonan">
                            ${line.permohonan.idPermohonan}
                        </display:column>
                        <display:column title="Nama">
                            <c:forEach items="${line.permohonan.senaraiPihak}" var="i">
                                ${i.pihak.nama}
                            </c:forEach>
                        </display:column>
                        <display:column title="No Pengenalan">
                            <c:forEach items="${line.permohonan.senaraiPihak}" var="i">
                                ${i.pihak.noPengenalan}
                            </c:forEach>
                        </display:column>
                        <display:column title="Alamat">
                            <c:forEach items="${line.permohonan.senaraiPihak}" var="i">
                                ${i.pihak.alamat1}
                                <c:if test="${i.pihak.alamat2 ne null}">, </c:if>
                                ${i.pihak.alamat2}
                                <c:if test="${i.pihak.alamat3 ne null}">, </c:if>
                                ${i.pihak.alamat3}
                                <c:if test="${i.pihak.alamat4 ne null}">, </c:if>
                                ${i.pihak.alamat4}
                            </c:forEach>
                        </display:column>
                        <display:column title="Poskod">
                            <c:forEach items="${line.permohonan.senaraiPihak}" var="i">
                                ${i.pihak.poskod}
                            </c:forEach>
                        </display:column>
                        <display:column title="Negeri">
                            <c:forEach items="${line.permohonan.senaraiPihak}" var="i">
                                ${i.pihak.negeri.nama}
                            </c:forEach>
                        </display:column>
                        <c:if test="${!actionBean.disPemohonController.tPemohon}">
                            <display:column title="Tindakan" >
                                <c:forEach items="${line.permohonan.senaraiPihak}" var="i">
                                    <a onclick="showMaklumatLuarNegeri('',${i.pihak.idPihak},'view')" onmouseover="this.style.cursor='pointer';" title="Maklumat Tanah Pemohon/Pemilik"><img alt="Maklumat Tanah Pemohon/Pemilik"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/land.png'/></a> |
                                        <c:if test="${i.pihak.jenisPengenalan.kod eq 'B' || i.pihak.jenisPengenalan.kod eq 'L' || i.pihak.jenisPengenalan.kod eq 'P' || i.pihak.jenisPengenalan.kod eq 'T' || i.pihak.jenisPengenalan.kod eq 'I'}">
                                        <a onclick="showKeluarga('')" onmouseover="this.style.cursor='pointer';" title="Maklumat Keluarga"><img alt="Maklumat Keluarga"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/family2.png'/></a>
                                        </c:if>
                                        <c:if test="${i.pihak.jenisPengenalan.kod eq 'S'|| i.pihak.jenisPengenalan.kod eq 'U'}">
                                        <a onclick="showPengarah('')" onmouseover="this.style.cursor='pointer';" title="Maklumat Pengarah"><img alt="Maklumat Pengarah"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/corporate.png'/></a>
                                        </c:if>
                                    </c:forEach>

                            </display:column>
                        </c:if>
                        
                    </display:table>
                </c:if>

                
                <c:if test="${fn:length(actionBean.senaraiKelompok) eq 0}">
                    <display:table class="tablecloth" name="${actionBean.listPemohon}" cellpadding="0" cellspacing="0" id="line"
                                   requestURI="/pelupusan/lelong_awam">
                        <display:column title="Bil" sortable="true">${line_rowNum}
                            <s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>
                        </display:column>
                        <display:column property="pihak.nama" title="Nama"/>
                        <display:column property="pihak.noPengenalan" title="Nombor Pengenalan" />
                        <display:column title="Alamat" >
                            ${line.pihak.alamat1}
                            <c:if test="${line.pihak.alamat2 ne null}">, </c:if>
                            ${line.pihak.alamat2}
                            <c:if test="${line.pihak.alamat3 ne null}">, </c:if>
                            ${line.pihak.alamat3}
                            <c:if test="${line.pihak.alamat4 ne null}">, </c:if>
                            ${line.pihak.alamat4}
                        </display:column>
                        <display:column property="pihak.poskod" title="Poskod" />
                        <display:column property="pihak.negeri.nama" title="Negeri" />
                        <c:if test="${!actionBean.disPemohonController.tPemohon}">
                            <display:column title="Tindakan" >
                                <a onclick="showMaklumatLuarNegeri(${line.idPemohon},${line.pihak.idPihak},'view')" onmouseover="this.style.cursor='pointer';" title="Maklumat Tanah Pemohon/Pemilik"><img alt="Maklumat Tanah Pemohon/Pemilik"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/land.png'/></a> |
                                    <c:if test="${line.pihak.jenisPengenalan.kod eq 'B' || line.pihak.jenisPengenalan.kod eq 'L' || line.pihak.jenisPengenalan.kod eq 'P' || line.pihak.jenisPengenalan.kod eq 'T' || line.pihak.jenisPengenalan.kod eq 'I'}">
                                    <a onclick="showKeluarga(${line.idPemohon})" onmouseover="this.style.cursor='pointer';" title="Maklumat Keluarga"><img alt="Maklumat Keluarga"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/family2.png'/></a>
                                    </c:if>
                                    <c:if test="${line.pihak.jenisPengenalan.kod eq 'S'|| line.pihak.jenisPengenalan.kod eq 'U'}">
                                    <a onclick="showPengarah(${line.idPemohon})" onmouseover="this.style.cursor='pointer';" title="Maklumat Pengarah"><img alt="Maklumat Pengarah"  height="20px" width="20px" src='${pageContext.request.contextPath}/pub/images/icons/corporate.png'/></a>
                                    </c:if>
                                </display:column>
                            </c:if>
                         <c:if test="${actionBean.stageId eq '21BerjayaLelong'}">
                            <display:column title="Berjaya Bida" >
                                <c:if test="${actionBean.lelong_awam.pemohon ne null}">
                                    <c:if test="${actionBean.lelong_awam.pemohon.idPemohon eq line.idPemohon}">
                                        <input type="radio" name="sid" id="sid" checked onclick="simpankeputusan('${line.idPemohon}')" >
                                    </c:if>
                                    <c:if test="${actionBean.lelong_awam.pemohon.idPemohon ne line.idPemohon}">
                                        <input type="radio" name="sid" id="sid" onclick="simpankeputusan('${line.idPemohon}')" >
                                    </c:if>
                                </c:if>
                                <c:if test="${actionBean.lelong_awam.pemohon eq null}">
                                        <input type="radio" name="sid" id="sid" onclick="simpankeputusan('${line.idPemohon}')" >
                                </c:if>
                                    
                            </display:column>
                        </c:if>
                        </display:table>
                    </c:if>
                  
            </fieldset> <br>
        </div>
    </div>
                        <br><br>
                  <c:if test="${actionBean.lelong_awam.pemohon ne null}">
                      
                          <div class="subtitle">
                            <fieldset class="aras1">
                                <legend>Pembida Yang Berjaya</legend>
                                <br>
                                <p>
                                    <label>Nama :</label>
                                    ${actionBean.lelong_awam.pemohon.pihak.nama}
                                </p>

                                <p>
                                    <label>No Pengenalan :</label>
                                    ${actionBean.lelong_awam.pemohon.pihak.noPengenalan}
                                </p>
                                <p>
                                    <label><em>*</em>Harga Bida (RM):</label>
                                    <s:text name="hrg_bida" id="hrg_bida" value="${actionBean.lelong_awam.hargaBidaan}" size="20" onkeyup="validateNumber(this,this.value);" />
                                </p>
                                <br>
                                <p>
                                    <label>&nbsp;</label>
                                    <s:button name="simpanhargabida" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                                </p>
                            </fieldset >
                        </div>
                  </c:if>
</s:form>
