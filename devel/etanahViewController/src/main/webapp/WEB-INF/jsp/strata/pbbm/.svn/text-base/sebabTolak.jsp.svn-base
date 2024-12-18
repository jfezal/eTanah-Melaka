<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">

</script>
<s:form beanclass="etanah.view.strata.SebabTolak">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Kemasukan Butiran Surat
            </legend>
            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PKKR'}">
                <c:if test="${actionBean.mohonFasa.keputusan.kod eq 'L'}">
                    <br />
                    <p>2. Sukacita dimaklumkan bahawa permohonan tuan berhubung perkara diatas telah diluluskan
                        oleh Pengarah Tanah dan Galian, Melaka.
                    </p>  
                    <br />
                </c:if>
                <c:if test="${actionBean.mohonFasa.keputusan.kod eq 'T'}">
                    <br />
                    <p>2. Dukacita dimaklumkan bahawa permohonan tuan berhubung perkara diatas telah ditolak
                        oleh Pengarah Tanah dan Galian, Melaka.
                    </p>  
                    <br />
                </c:if>
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PKKR' || actionBean.permohonan.kodUrusan.kod eq 'SUBMC'}">
                <p>
                    <label>COB :</label>
                <s:select name="nomborRujukan" value = "${actionBean.mohonKertas.nomborRujukan}">
                    <s:option value="">Pilih ...</s:option>
                    <s:option value="1">MBMB</s:option>
                    <s:option value="2">MPHTJ</s:option>
                    <s:option value="3">MPJ</s:option>
                    <s:option value="4">MPAG</s:option>
                </s:select>
                <br/>
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PKKR'}">
                </p>
                <c:if test="${actionBean.mohonFasa.keputusan.kod eq 'L'}">
                    <br />
                    <p>2. Sukacita dimaklumkan bahawa permohonan tuan untuk mendapatkan Sijil Bangunan Kos Rendah 
                        telah diluluskan oleh Pengarah Tanah dan Galian, Melaka pada ${actionBean.trhKpsn}
                    </p>  
                    <br />
                </c:if>
                <c:if test="${actionBean.mohonFasa.keputusan.kod eq 'T'}">
                    <br />
                    <p>2. Dukacita dimaklumkan bahawa permohonan tuan untuk mendapatkan Sijil Bangunan Kos Rendah 
                        telah ditolak oleh Pengarah Tanah dan Galian, Melaka kerana :-
                    </p>  
                    <br />
                </c:if>
            </c:if>

            <br />
            <p> 
            <s:textarea  name="kandungan" cols="90"  rows="5" class="normal_text"/>
            </p>
            <br/>
            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PKKR' && actionBean.permohonan.kodUrusan.kod ne 'PBBS' 
                          && actionBean.permohonan.kodUrusan.kod ne 'PBBD' && actionBean.permohonan.kodUrusan.kod ne 'PBS' 
                          && actionBean.permohonan.kodUrusan.kod ne 'PSBS' && actionBean.permohonan.kodUrusan.kod ne 'PHPP' 
                          && actionBean.permohonan.kodUrusan.kod ne 'PHPC' && actionBean.permohonan.kodUrusan.kod ne 'SUBMC' }">
                <label><s:button name="simpanSebabTolak" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/></label>
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PKKR'}">
                <label><s:button name="simpanSebabTolakPKKR" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/></label>
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'SUBMC'}">
                <label><s:button name="simpanSebabTolakSUBMC" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/></label>
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PBBS' || actionBean.permohonan.kodUrusan.kod eq 'PBBD'
                          || actionBean.permohonan.kodUrusan.kod eq 'PBS' || actionBean.permohonan.kodUrusan.kod eq 'PSBS'
                          || actionBean.permohonan.kodUrusan.kod eq 'PHPP' || actionBean.permohonan.kodUrusan.kod eq 'PHPC'}">
                <label><s:button name="simpanSebabTolakPBBS" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/></label>
            </c:if>
        </fieldset>

    </div>

</s:form>