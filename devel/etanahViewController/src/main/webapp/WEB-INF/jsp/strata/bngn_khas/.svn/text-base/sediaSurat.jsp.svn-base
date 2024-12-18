<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">

</script>
<s:form beanclass="etanah.view.strata.MaklumanSurat">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Kemasukan Butiran Surat
            </legend>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PKBK'}">
                <p>
                    <label>Bahagian Kawalan Bangunan dan Perancang Majlis :</label>
                <s:select name="nomborRujukan" value = "${actionBean.mohonKertas.nomborRujukan}">
                    <s:option value="">Pilih ...</s:option>
                    <s:option value="1">MBMB</s:option>
                    <s:option value="2">MPHTJ</s:option>
                    <s:option value="3">MPJ</s:option>
                    <s:option value="4">MPAG</s:option>
                </s:select>
                <br/>
            </c:if>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PKBK'}">
                </p>
                <c:if test="${actionBean.mohonFasa.keputusan.kod eq 'L'}">
                    <br />
                    <p>2. Sukacita dimaklumkan bahawa permohonan tuan untuk mendapatkan Sijil Bangunan Khas 
                        telah diluluskan oleh Majlis Mesyuarat Kerajaan Negeri Melaka pada ${actionBean.trhKpsn}
                    </p>  
                    <br />
                </c:if>
                <c:if test="${actionBean.mohonFasa.keputusan.kod eq 'T'}">
                    <br />
                    <p>2. Dukacita dimaklumkan bahawa permohonan tuan untuk mendapatkan Sijil Bangunan Khas 
                        telah ditolak oleh Majlis Mesyuarat Kerajaan Negeri Melaka kerana :-
                    </p>  
                    <br />
                </c:if>
            </c:if>

            <br />
            <p> 
            <s:textarea  name="kandungan" cols="90"  rows="5" class="normal_text"/>
            </p>
            <br/>
            
            <br />
            <p> Sekian, terima kasih.</p>
            <br />
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PKBK'}">
                <label><s:button name="simpanSebabPKBK" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/></label>
            </c:if>
        </fieldset>

    </div>

</s:form>