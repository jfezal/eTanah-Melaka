<%-- 
    Document   : pelarasan
    Created on : May 17, 2010, 12:21:57 PM
    Author     : abdul.hakim
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//Dlabel HTML 4.01 pansitional//EN" http://www.w3.org/p/html4/loose.dlabel">
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script  language="javascript" >
    $(document).ready(function() {
        total();
    });
    
    function edit(f, id1){
        var queryString = $(f).formSerialize()

        window.open("${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?hakmilikDetail&"+queryString+"&hakmilik.idHakmilik="+id1, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=1035,height=200");
    }

    function edit2(f, id1 ,id2){
        // var queryString = $(f).formSerialize()

        window.open("${pageContext.request.contextPath}/hasil/pertanyaan_hakmilik?pembayarDetail&idPihak="+id1+"&idHakmilik="+id2, "eTanah",
        "status=0,toolbar=0,resizable=1,scrollbars=1,location=0,menubar=0,width=1035,height=420");
    }

    function click1(id){
        $('#'+id).removeAttr("style");
        $('#'+id).attr("style", "text-align:right");
    }

    function blur1(id){
        $('#'+id).removeAttr("style");
        $('#'+id).attr("style", "background:transparent;border:0 px;cursor:pointer");
        var x = document.getElementById(id);
        var f = parseFloat(x.value);
        var a = document.getElementById(id)
        if ((isNaN(a.value))||((a.value) =="")){
            alert("Nombor tidak sah");
            $('#'+id).val("0.00");
            return;
        }else{
            $('#'+id).val(f.toFixed(2));
        }
        total();
    }

    function total(){
        var cukai           = document.getElementById('cukaiSebenar');
        var debit           = document.getElementById('debit');
        var denda         = document.getElementById('denda');
        var kredit          = document.getElementById('kredit');
        var tunggakan  = document.getElementById('tunggakan');
        var remisyen     = document.getElementById('remisyen');
        var notis           = document.getElementById('notis');

        var total = parseFloat(cukai.value) + parseFloat(debit.value) + parseFloat(denda.value) - parseFloat(kredit.value) + parseFloat(tunggakan.value) -
            parseFloat(remisyen.value) + parseFloat(notis.value);

        $('#total').val(total.toFixed(2));

    }

</script>
<s:form beanclass="etanah.view.stripes.hasil.PelarasanActionBean">
<s:messages/>
<s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Asas</legend>
            <c:if test="${actionBean.kodNegeri eq 'melaka'}">
                <p>
                    <label> No Akaun :</label>
                    ${actionBean.akaun.noAkaun}&nbsp;
                </p>
            </c:if>

            <p>
                <label>ID Hakmilik :</label>
                ${actionBean.hakmilik.idHakmilik}&nbsp;
            </p>

            <p>
                <label>Daerah :</label>
                ${actionBean.hakmilik.daerah.nama}&nbsp;
            </p>

            <p>
                <label>Bandar/Pekan/Mukim :</label>
                ${actionBean.hakmilik.bandarPekanMukim.nama}&nbsp;
            </p>

            <p>
                <label>Jenis Hakmilik :</label>
                ${actionBean.hakmilik.kodHakmilik.nama}&nbsp;
            </p>

            <p>
                <label>Jenis Penggunaan Tanah :</label>
                ${actionBean.hakmilik.kategoriTanah.nama}&nbsp;
            </p>

            <p>
                <label>Kod Lot :</label>
                ${actionBean.hakmilik.lot.nama}&nbsp;

            </p>

            <p>
                <label>No Lot/PT :</label>
                ${actionBean.hakmilik.noLot}&nbsp;

            </p>

            <p>
                <label>Tarikh Daftar :</label>
                <fmt:formatDate type="date"
                                pattern="dd/MM/yyyy"
                                value="${actionBean.hakmilik.tarikhDaftar}"/>&nbsp;
            </p>

            <p>
                <label>Tanah Rezab :</label>
                ${actionBean.hakmilik.rizab.nama}&nbsp;
            </p>

            <p>
                <label>Pihak Berkuasa Tempatan :</label>
                ${actionBean.hakmilik.pbt.nama}&nbsp;
            </p>

            <p>
                <label>Taraf Pegangan :</label>
                <c:if test="${actionBean.hakmilik.pegangan eq 'P'}">
                    Pajakan
                </c:if>
                <c:if test="${actionBean.hakmilik.pegangan eq 'S'}">
                    Selama - lama
                </c:if>
                &nbsp;
            </p>
            
            <p>
                <label>Tempoh :</label>
                <c:if test="${actionBean.hakmilik.tempohPegangan ne null}">
                    ${actionBean.hakmilik.tempohPegangan} Tahun&nbsp;
                </c:if>
                <c:if test="${actionBean.hakmilik.tempohPegangan eq null}">
                    Tiada
                </c:if>
            </p>

            <p>
                <label>Tarikh Luput :</label>
                <c:if test="${actionBean.hakmilik.tarikhLuput eq null}">
                    Tiada
                </c:if>
                <fmt:formatDate type="date"
                                pattern="dd/MM/yyyy"
                                value="${actionBean.hakmilik.tarikhLuput}"/>&nbsp;
            </p>
            <p>
                <label for="noPu">No Permohonan Ukur :</label>
                ${actionBean.hakmilik.noPu}&nbsp;
            </p>
            
            <p>
                <label >Syarat Nyata :</label>
                <a href="#" onclick="edit(this.form, '${actionBean.hakmilik.idHakmilik}');">${actionBean.hakmilik.syaratNyata.kod}&nbsp;</a>
            </p>

            <p>
                <label>No Pelan Piawai :</label>
                ${actionBean.hakmilik.noLitho}&nbsp;
            </p>
            
            <p>
                <label>No Pelan Akui :</label>
                ${actionBean.hakmilik.noPelan}&nbsp;
            </p>

            <p>
                <label >Keluasan  :</label>
                <s:format formatPattern="#,##0.0000" value="${actionBean.hakmilik.luas}"/>&nbsp; ${actionBean.hakmilik.kodUnitLuas.nama}
            </p>

            <p>
                <label >Cukai Tanah Tahunan (RM)  :</label>
                <fmt:formatNumber value="${actionBean.cukaiAsal}" pattern="0.00"/>&nbsp;
            </p>

            <p>
                <label>Status Hakmilik:</label>
                ${actionBean.hakmilik.kodStatusHakmilik.nama}&nbsp;
            </p>
        </fieldset>
    </div>
    <p></p>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Pembayar</legend>
            <p><label>Nama :</label>
                <%--<a href="#" onclick="edit2(this.form, '${actionBean.akaun.pemegang.idPihak}','${actionBean.hakmilik.idHakmilik}');">${actionBean.akaun.pemegang.nama}&nbsp;</a>&nbsp;--%>
                ${actionBean.akaun.pemegang.nama}&nbsp;
            </p>
            <p>
                <label>Alamat Tetap :</label>
                ${actionBean.akaun.pemegang.alamat1}
            </p>
            <p>
                <label> &nbsp;</label>
                ${actionBean.akaun.pemegang.alamat2}
            </p>
            <p>
                <label> &nbsp;</label>
                ${actionBean.akaun.pemegang.alamat3}
            </p>
            <p>
                <label> &nbsp;</label>
                ${actionBean.akaun.pemegang.alamat4}&nbsp;
            </p>

            <p>
                <label>Poskod :</label>
                ${actionBean.akaun.pemegang.poskod}&nbsp;
            </p>
            <p>
                <label>Negeri :</label>
                ${actionBean.akaun.pemegang.negeri.nama}&nbsp;

            </p>
            <p>
                <label>Alamat Surat Menyurat :</label>
                ${actionBean.akaun.pemegang.suratAlamat1}
            </p>
            <p>
                <label> &nbsp;</label>
                ${actionBean.akaun.pemegang.suratAlamat2}
            </p>
            <p>
                <label> &nbsp;</label>
                ${actionBean.akaun.pemegang.suratAlamat3}
            </p>
            <p>
                <label> &nbsp;</label>
                ${actionBean.akaun.pemegang.suratAlamat4}&nbsp;
            </p>

            <p>
                <label>Poskod :</label>
                ${actionBean.akaun.pemegang.suratPoskod}&nbsp;
            </p>
            <p>
                <label>Negeri :</label>
                ${actionBean.akaun.pemegang.suratNegeri.nama}&nbsp;

            </p>
            <p>
                <label>Nombor Telefon :</label>
                ${actionBean.akaun.pemegang.noTelefon1}&nbsp;

            </p>
            <p>
                <label>Nombor Telefon Bimbit:</label>
                ${actionBean.akaun.pemegang.noTelefonBimbit}&nbsp;
            </p>
            <p>
                <label>Email:</label>
                ${actionBean.akaun.pemegang.email}
            </p>
        </fieldset>
    </div>
    <p></p>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Denda & Notis
            </legend>
            <p class=instr><font color="black">
                        <font color="red">PERINGATAN:</font> Sila klik pada amaun untuk membuat pelarasan.</font>
            </p>
            <div align="center">
                <table align="center" border="0">
                    <tr>
                        <td width="170"><font color="#003194" size="2px" style="Tahoma"><b>Cukai Semasa </b></font></td>
                        <td width="2"><font color="#003194" size="2px" style="Tahoma"><b>:</b></font></td>
                        <td><font size="2">RM <s:text name="cukai" id="cukaiSebenar"  formatPattern="#,##0.00"
                                        onclick="click1('cukaiSebenar')" onblur="blur1('cukaiSebenar')"
                                        style="background:transparent;border:0 px;cursor:pointer" size="15"/></font></td>
                        <td width="5">&nbsp;</td>
                        <td width="170"><font color="#003194" size="2px" style="Tahoma"><b>Debit </b></font></td>
                        <td width="2"><font color="#003194" size="2px" style="Tahoma"><b>:</b></font></td>
                        <td><font size="2">RM <s:text name="debit" formatPattern="#,##0.00"
                                                    onclick="click1('debit')" onblur="blur1('debit')" id="debit"
                                                    style="background:transparent;border:0 px;cursor:pointer" size="15"/></font></td>
                    </tr>
                    <tr>
                        <td width="170"><font color="#003194" size="2px" style="Tahoma"><b>Kredit</b></font></td>
                        <td width="2"><font color="#003194" size="2px" style="Tahoma"><b>:</b></font></td>
                        <td><font size="2">RM <s:text name="kredit" formatPattern="#,##0.00"
                                                    onclick="click1('kredit')" onblur="blur1('kredit')" id="kredit"
                                                    style="background:transparent;border:0 px;cursor:pointer" size="15"/></font></td>
                        <td width="5">&nbsp;</td>
                        <td width="170"><font color="#003194" size="2px" style="Tahoma"><b>Tunggakan Denda Lewat </b></font></td>
                        <td width="2"><font color="#003194" size="2px" style="Tahoma"><b>:</b></font></td>
                        <td><font size="2">RM <s:text name="denda" formatPattern="#,##0.00"
                                                    onclick="click1('denda')" onblur="blur1('denda')" id="denda"
                                                    style="background:transparent;border:0 px;cursor:pointer" size="15"/></font></td>
                    </tr>
                    <tr>
                        <td width="170"><font color="#003194" size="2px" style="Tahoma"><b>Tunggakan Cukai Tanah</b></font></td>
                        <td width="2"><font color="#003194" size="2px" style="Tahoma"><b>:</b></font></td>
                        <td><font size="2">RM <s:text name="tunggakan" formatPattern="#,##0.00"
                                                    onclick="click1('tunggakan')" onblur="blur1('tunggakan')" id="tunggakan"
                                                    style="background:transparent;border:0 px;cursor:pointer;" size="15"/></font></td>
                        <td width="5">&nbsp;</td>
                        <td width="170"><font color="#003194" size="2px" style="Tahoma"><b>Remisyen </b></font></td>
                        <td width="2"><font color="#003194" size="2px" style="Tahoma"><b>:</b></font></td>
                        <td><font size="2">RM <s:text name="remisyen" formatPattern="#,##0.00"
                                                    onclick="click1('remisyen')" onblur="blur1('remisyen')" id="remisyen"
                                                    style="background:transparent;border:0 px;" size="15"/></font></td>
                    </tr>
                    <tr>
                        <td width="170"><font color="#003194" size="2px" style="Tahoma"><b>Notis 6A </b></font></td>
                        <td width="2"><font color="#003194" size="2px" style="Tahoma"><b>:</b></font></td>
                        <td><font size="2">RM <s:text name="notis" formatPattern="#,##0.00"
                                                    onclick="click1('notis')" onblur="blur1('notis')" id="notis"
                                                    style="background:transparent;border:0 px;" size="15"/></font></td>
                        <td width="5">&nbsp;</td>
                        <td width="170"><font color="#003194" size="2px" style="Tahoma"><b>&nbsp; </b></font></td>
                        <td width="2"><font color="#003194" size="2px" style="Tahoma"><b>&nbsp;</b></font></td>
                        <td><font size="2">&nbsp;</font></td>
                    </tr>
                </table>
                <hr width="60%">
                <table align="center" border="0">
                    <tr>
                        <td width="90"><font color="#003194" size="2px" style="Tahoma"><b>Jumlah Besar </b></font></td>
                        <td width="2"><font color="#003194" size="2px" style="Tahoma"><b>:</b></font></td>
                        <td><font size="2">RM <s:text name="total" id="total" formatPattern="0.00" style="background:transparent;border:0 px;" readonly="true"/></font></td>
                    </tr>
                </table>
                <%--<hr width="60%">--%>
                <p align="right">
                    <s:button class="btn" onclick="doSubmit(this.form, this.name, 'page_div');" name="save" value="Simpan"/>
                </p>
            </div>
        </fieldset>
    </div>
</s:form>