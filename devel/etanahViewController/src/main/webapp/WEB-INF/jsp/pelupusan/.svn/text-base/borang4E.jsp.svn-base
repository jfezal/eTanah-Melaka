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


<s:form beanclass="etanah.view.stripes.pelupusan.Borang4EActionBean">
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
                ${actionBean.permohonanTuntutanBayar.amaun}&nbsp;

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
    <c:if test="${edit eq true}">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Jadual
            </legend>
             <p>
                <label>No. Lesen :</label>
               <%-- <s:text name="permit.noPermit"/>--%>
               ${actionBean.permit.noPermit}&nbsp;

            </p>
            <p>
                <label><font color="red">*</font>Tarikh Mula Pajakan :</label>
                <s:text name="permitMula" class="datepicker" formatPattern="dd/MM/yyyy"/>
            </p>
            <p>
                <label><font color="red">*</font>Tarikh Tamat Pajakan :</label>
                <s:text name="permitAkhir" class="datepicker" formatPattern="dd/MM/yyyy"/>

            </p>
            <p>
                <label><font color="red">*</font>Tempoh Sah Lesen :</label>
                <s:text name="permit.tempohSah" size="3"/> &nbsp; Tahun
                <!--<c:if test="${!empty actionBean.permit.tempohSahUom.nama}">${actionBean.permit.tempohSahUom.nama}</c:if>
                 <c:if test="${empty actionBean.permit.tempohSahUom.nama}">Tahun</c:if>-->
                 <%--<s:select name="permit.tempohSahUom.kod">
                    <s:option value="">Sila Pilih</s:option>
                    <s:option value="T">Tahun</s:option>
                    <s:option value="B">Bulan</s:option>
                    <s:option value="HR">Hari</s:option>
                </s:select>--%>
            </p>
            <p>
                <label><font color="red">*</font>Bayaran Pajakan (RM):</label>
                <s:text name="permit.keterangan" />
            </p>
            <!--<p>
                <label><font color="red">*</font>Tidak Lewat Dari :</label>
                <s:select name="permitInfoPerbaharui.hariAkhirBayaran" style="width=50px">
                    <s:option value="">Sila Pilih</s:option>
                    <s:option value="1">1</s:option><s:option value="2">2</s:option><s:option value="3">3</s:option><s:option value="4">4</s:option><s:option value="5">5</s:option>
                    <s:option value="6">6</s:option><s:option value="7">7</s:option><s:option value="8">8</s:option><s:option value="9">9</s:option><s:option value="10">10</s:option>
                    <s:option value="11">11</s:option><s:option value="12">12</s:option><s:option value="13">13</s:option><s:option value="14">14</s:option><s:option value="15">15</s:option>
                    <s:option value="16">16</s:option><s:option value="17">17</s:option><s:option value="18">18</s:option><s:option value="19">19</s:option><s:option value="20">20</s:option>
                    <s:option value="21">21</s:option><s:option value="22">22</s:option><s:option value="23">23</s:option><s:option value="24">24</s:option><s:option value="25">25</s:option>
                    <s:option value="26">26</s:option><s:option value="27">27</s:option><s:option value="28">28</s:option><s:option value="29">29</s:option><s:option value="30">30</s:option>
                    <s:option value="31">31</s:option>
                </s:select>
                /
                <s:select name="permitInfoPerbaharui.bulanAkhirBayaran" style="width=50px">
                    <s:option value="">Sila Pilih</s:option>
                    <s:option value="1">1</s:option><s:option value="2">2</s:option><s:option value="3">3</s:option><s:option value="4">4</s:option><s:option value="5">5</s:option>
                    <s:option value="6">6</s:option><s:option value="7">7</s:option><s:option value="8">8</s:option><s:option value="9">9</s:option><s:option value="10">10</s:option>
                    <s:option value="11">11</s:option><s:option value="12">12</s:option>
                </s:select>
                Setiap Tahun
            </p>
            <p>
                <label><font color="red">*</font>Jenis Tanah Dirizabkan :</label>
                <s:select name="mohonHakmilik.statusLuasDiluluskan" id="kodmilik" value="${actionBean.mohonHakmilik.statusLuasDiluluskan}">
                    <s:option value="">Sila Pilih</s:option>
                    <s:option value="S">Keseluruhan </s:option>
                    <s:option value="B">Bahagian</s:option>
                </s:select>
            </p>-->
<!--            <p>
                <label><font color="red">*</font>Jenis Struktur :</label>
                <s:text name="permitStrukLulus.jenisStruktur" size="50"/>
            </p>
            <p>
                <label><font color="red">*</font>Lokasi :</label>
                <s:text name="mohonHakmilik.lokasi" size="50"/>
            </p>
            <p>
                <label>Isipadu Diluluskan:</label>
                    ${actionBean.mohonHakmilik.luasDiluluskan}&nbsp; ${actionBean.mohonHakmilik.luasLulusUom.nama}&nbsp;
            </p>
            <p>
                <label>Tempoh :</label>
                    ${actionBean.mohonHakmilik.tempohPajakan}&nbsp; Tahun. 
            </p>
            <p>
                <label><font color="red">*</font>Keluasan Terlibat(volume) :</label>
                <s:text name="permitStrukLulus.luasStruktur" formatPattern="#,###,##0.0000" maxlength="20" id="Luas"/>
                <s:select name="luasKodUom" value="${actionBean.luasKodUom}" id="idluasKodUom">
                    <s:option value="">Sila Pilih</s:option>
                    <s:option value="MP">Meter Padu</s:option>
                </s:select>
            </p>-->
            <p>
                <label>Peruntukan Tambahan :</label>
                <s:textarea name="peruntukanTambahan" rows="5" cols="40"/>
            </p>
            <br>
              <p>
            <label>&nbsp;</label>

            <s:button name="simpanPermit" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name,'page_div')"/>
        </p>
        </fieldset>
    </div>
  </c:if>
    
    <c:if test="${edit eq false}">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Jadual
            </legend>
             <p>
                <label>No. Lesen :</label>
               <%-- <s:text name="permit.noPermit"/>--%>
               ${actionBean.permit.noPermit}&nbsp;

            </p>
            <p>
                <label><font color="red">*</font>Tarikh Mula Pajakan :</label>
                <s:text name="permitMula" formatPattern="dd/MM/yyyy"  readonly="true"/>
            </p>
            <p>
                <label><font color="red">*</font>Tarikh Tamat Pajakan :</label>
                <s:text name="permitAkhir" formatPattern="dd/MM/yyyy" readonly="true"/>

            </p>
            <p>
                <label><font color="red">*</font>Tempoh Sah Lesen :</label>
                ${actionBean.permit.tempohSah} &nbsp; Tahun
                <!--<c:if test="${!empty actionBean.permit.tempohSahUom.nama}">${actionBean.permit.tempohSahUom.nama}</c:if>
                 <c:if test="${empty actionBean.permit.tempohSahUom.nama}">Tahun</c:if>-->
                 <%--<s:select name="permit.tempohSahUom.kod">
                    <s:option value="">Sila Pilih</s:option>
                    <s:option value="T">Tahun</s:option>
                    <s:option value="B">Bulan</s:option>
                    <s:option value="HR">Hari</s:option>
                </s:select>--%>
            </p>
            <p>
                <label><font color="red">*</font>Bayaran Pajakan (RM):</label>
                ${actionBean.permit.keterangan}
            </p>
            <!--<p>
                <label><font color="red">*</font>Tidak Lewat Dari :</label>
                <s:select name="permitInfoPerbaharui.hariAkhirBayaran" style="width=50px">
                    <s:option value="">Sila Pilih</s:option>
                    <s:option value="1">1</s:option><s:option value="2">2</s:option><s:option value="3">3</s:option><s:option value="4">4</s:option><s:option value="5">5</s:option>
                    <s:option value="6">6</s:option><s:option value="7">7</s:option><s:option value="8">8</s:option><s:option value="9">9</s:option><s:option value="10">10</s:option>
                    <s:option value="11">11</s:option><s:option value="12">12</s:option><s:option value="13">13</s:option><s:option value="14">14</s:option><s:option value="15">15</s:option>
                    <s:option value="16">16</s:option><s:option value="17">17</s:option><s:option value="18">18</s:option><s:option value="19">19</s:option><s:option value="20">20</s:option>
                    <s:option value="21">21</s:option><s:option value="22">22</s:option><s:option value="23">23</s:option><s:option value="24">24</s:option><s:option value="25">25</s:option>
                    <s:option value="26">26</s:option><s:option value="27">27</s:option><s:option value="28">28</s:option><s:option value="29">29</s:option><s:option value="30">30</s:option>
                    <s:option value="31">31</s:option>
                </s:select>
                /
                <s:select name="permitInfoPerbaharui.bulanAkhirBayaran" style="width=50px">
                    <s:option value="">Sila Pilih</s:option>
                    <s:option value="1">1</s:option><s:option value="2">2</s:option><s:option value="3">3</s:option><s:option value="4">4</s:option><s:option value="5">5</s:option>
                    <s:option value="6">6</s:option><s:option value="7">7</s:option><s:option value="8">8</s:option><s:option value="9">9</s:option><s:option value="10">10</s:option>
                    <s:option value="11">11</s:option><s:option value="12">12</s:option>
                </s:select>
                Setiap Tahun
            </p>
            <p>
                <label><font color="red">*</font>Jenis Tanah Dirizabkan :</label>
                <s:select name="mohonHakmilik.statusLuasDiluluskan" id="kodmilik" value="${actionBean.mohonHakmilik.statusLuasDiluluskan}">
                    <s:option value="">Sila Pilih</s:option>
                    <s:option value="S">Keseluruhan </s:option>
                    <s:option value="B">Bahagian</s:option>
                </s:select>
            </p>-->
<!--            <p>
                <label><font color="red">*</font>Jenis Struktur :</label>
                <s:text name="permitStrukLulus.jenisStruktur" size="50"/>
            </p>
            <p>
                <label><font color="red">*</font>Lokasi :</label>
                <s:text name="mohonHakmilik.lokasi" size="50"/>
            </p>
            <p>
                <label>Isipadu Diluluskan:</label>
                    ${actionBean.mohonHakmilik.luasDiluluskan}&nbsp; ${actionBean.mohonHakmilik.luasLulusUom.nama}&nbsp;
            </p>
            <p>
                <label>Tempoh :</label>
                    ${actionBean.mohonHakmilik.tempohPajakan}&nbsp; Tahun. 
            </p>
            <p>
                <label><font color="red">*</font>Keluasan Terlibat(volume) :</label>
                <s:text name="permitStrukLulus.luasStruktur" formatPattern="#,###,##0.0000" maxlength="20" id="Luas"/>
                <s:select name="luasKodUom" value="${actionBean.luasKodUom}" id="idluasKodUom">
                    <s:option value="">Sila Pilih</s:option>
                    <s:option value="MP">Meter Padu</s:option>
                </s:select>
            </p>-->
            <p>
                <label>Peruntukan Tambahan :</label>
                <s:textarea name="peruntukanTambahan" rows="5" cols="40" readonly="true"/>
            </p>
        </fieldset>
    </div>
  </c:if>
</s:form>
