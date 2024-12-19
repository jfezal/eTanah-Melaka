<%--
    Document   : penyediaan_borang4A
    Created on : May 7, 2010, 5:28:52 PM
    Author     : nurul.izza
    modified by: sitifariza.hanim on 11102010
    Modified by: Murali on 23/05/2011
--%>

<%--<%@page contentType="text/html" pageEncoding="windows-1252"%>--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<script type="text/javascript">
    function test(f)
    {
        $(f).clearForm();
    }


</script>


<s:form beanclass="etanah.view.stripes.pelupusan.Borang4DeActionBean">
 <s:hidden name="lupusPermit.id"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <s:messages/>
            <s:errors/>
            <legend>
                Maklumat Bayaran
            </legend>
           
            <p>
                <label>Bayaran (RM) :</label>
                <%--<s:text name="lupusPermit.bayaran" value="${actionBean.bayaran}"/>--%>
                <s:format formatPattern="#,##0.00" value="${actionBean.permohonanTuntutanBayar.amaun}"/>&nbsp;

            </p>
            <p>
                <label>Nombor Resit :</label>
                <%--<s:text name="lupusPermit.noResit" size="15" value="${actionBean.noResit}" />--%>
                ${actionBean.permohonanTuntutanBayar.dokumenKewangan.idDokumenKewangan}&nbsp;
            </p>
            <p>
                <label>Tarikh Bayaran :</label>
               <%--${actionBean.permohonanTuntutanBayar.tarikhBayar}&nbsp;--%>
                <fmt:formatDate value="${actionBean.permohonanTuntutanBayar.tarikhBayar}" pattern="dd/MM/yyyy "/>&nbsp;
            </p>
            <br>             
        </fieldset>
    </div>
  
    <br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Jadual
            </legend>
             <p>
                <label>No. Permit :</label>
               <%-- <s:text name="permit.noPermit"/>--%>
               ${actionBean.permit.noPermit}&nbsp;

            </p>
            <p>
                <label><font color="red">*</font>Tarikh Mula Permit :</label>
                <s:text name="permitMula" class="datepicker" formatPattern="dd/MM/yyyy"/>
            </p>
            <p>
                <label><font color="red">*</font>Tarikh Tamat Permit :</label>
                <s:text name="permitAkhir" class="datepicker" formatPattern="dd/MM/yyyy"/>

            </p>
            <p>
                <label><font color="red">*</font>Jenis Struktur :</label>
                <s:text name="permitStrukLulus.jenisStruktur" class="normal_text" size="50"/>
            </p>
            <p>
                <label><font color="red">*</font>Lokasi :</label>
                <s:text name="mohonHakmilik.lokasi" class="normal_text" size="50"/>
            </p>
            <p>
                <label>Luas Diluluskan:</label>
                    ${actionBean.mohonHakmilik.luasDiluluskan}&nbsp;${actionBean.mohonHakmilik.luasLulusUom.nama}&nbsp;
            </p>
            <p>
                <label>Tempoh :</label>
                    ${actionBean.mohonHakmilik.tempohPajakan}&nbsp;Tahun
            </p>
            <p>
                <label><font color="red">*</font>Keluasan Struktur Terlibat :</label>
                <s:text name="permitStrukLulus.luasStruktur" formatPattern="#,###,##0.0000" maxlength="20" id="Luas"/>
                <s:select name="luasKodUom" value="${actionBean.luasKodUom}" id="idluasKodUom">
                    <s:option value="">Sila Pilih</s:option>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPRU'}">
                        <s:option value="MP">Meter Padu</s:option>
                    </c:if>
                        <s:option value="M">Meter Persegi</s:option>
                </s:select>
            </p>
            <p>
                <label>Peruntukan Tambahan 1:</label>
                <s:textarea name="peruntukanTambahan" class="normal_text" rows="5" cols="50"/>
            </p>
            <br>
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PPRU'}">
                <c:if test="${actionBean.stageId eq '35SedPermit4De'}">
                    <p>
                        <label>&nbsp;</label>
                        <s:button name="simpanPermit" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name,'page_div')"/>
                    </p>
                </c:if>
                    <c:if test="${actionBean.stageId eq 'sedia_borang_4d' || actionBean.stageId eq 'semak_borang_4d' }">
                    <p>
                        <label>&nbsp;</label>
                        <s:button name="simpanPermit" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name,'page_div')"/>
                    </p>
                </c:if>
            </c:if>
                    
        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PPRU'}">
            <p>
            <label>&nbsp;</label>
            <s:button name="simpanPermit" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name,'page_div')"/>
        </p>
        </c:if>
        </fieldset>
    </div>
</s:form>
