<%-- 
    Document   : perihal_pembetulan
    Created on : 08-Oct-2009, 16:44:08
    Author     : md.nurfikri
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
    $(document).ready(function() {
        $("#vdoc").click( function(){
            frm = this.form;
            window.open(frm.action + "/vDoc?idPermohonan=${actionBean.permohonan.idPermohonan}", "eTanah","status=0,toolbar=0,location=0,menubar=0");
        });
    });
    function doEnabled(counter){
        if($('#chkbox'+counter).is(":checked"))
            $('#elem'+counter).removeAttr('disabled');
        else
            $('#elem'+counter).attr('disabled', 'true');
    }
</script>
<style type="text/css">
    .error { background-color: yellow; }
    .messages { background-color: yellow; }
</style>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.daftar.PembetulanActionBean">
    <s:messages/>    
    <s:hidden name="permohonan.idPermohonan"/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Perihal Pembetulan</legend>
            <p>
                <label for="Nota Pembetulan">Nota Pembetulan :</label>
                <s:textarea name="permohonan.catatan" rows="10" cols="50"/>
            </p>

        </fieldset>
    </div>
    <br/>
    <div>
        <fieldset class="aras1">
            <legend>Maklumat Asas Hakmilik</legend>
            <s:hidden name="hakmilik.idHakmilik"/>
            <%--<div class="content">
                <table class="tablecloth" align="center">
                    <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>Maklumat terdahulu</td>
                        <td>Maklumat terbaru</td>
                    </tr>
                    <tr>
                        <td><s:checkbox name="chkbox"/></td>
                        <td><label>Amaun Cukai</label></td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                     <tr>
                         <td><s:checkbox name="chkbox" id="chkbox1" onclick="doEnabled('1')" value="1"/></td>
                        <td><label>Cawangan</label></td>
                        <td>${actionBean.hakmilik.cawangan.name}</td>
                        <td><s:select name="hakmilik.cawangan.kod" id="elem1">
                                <s:options-collection collection="${list.senaraiKodCawangan}" label="name" value="kod"/>
                        </s:select></td>
                    </tr>
                     <tr>
                         <td><s:checkbox name="chkbox" id="chkbox1" onclick="doEnabled('1')" value="1"/></td>
                        <td><label>Daerah</label></td>
                        <td>${actionBean.hakmilik.daerah.nama}</td>
                        <td><s:select name="hakmilik.daerah.kod" id="elem1">
                                <s:options-collection collection="${list.senaraiKodDaerah}" label="nama" value="kod"/>
                        </s:select></td>
                    </tr>
                     <tr>
                        <td><s:checkbox name="chkbox" id="chkbox2" onclick="doEnabled('2')" value="2"/></td>
                        <td><label>Bandar/Pekan /Mukim</label></td>
                        <td>${actionBean.hakmilik.bandarPekanMukim.nama}</td>
                        <td><s:select name="hakmilik.bandarPekanMukim.kod" id="elem2">
                                <s:options-collection collection="${list.senaraiKodBandarMukim}" label="nama" value="kod"/>
                        </s:select></td>
                    </tr>
                     <tr>
                        <td><s:checkbox name="chkbox" id="chkbox3" onclick="doEnabled('3')" value="3"/></td>
                        <td><label>Seksyen</label></td>
                        <td>${actionBean.hakmilik.seksyen.nama}</td>
                        <td><s:select name="hakmilik.seksyen.kod" id="elem3">
                                <s:options-collection collection="${list.senaraiKodSeksyen}" label="nama" value="kod"/>
                        </s:select></td>
                    </tr>
                     <tr>
                        <td><s:checkbox name="chkbox" value="4"/></td>
                        <td><label>Jenis Hakmilik</label></td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                     <tr>
                        <td><s:checkbox name="chkbox" value="5"/></td>
                        <td><label>Kategori Tanah</label></td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                     <tr>
                        <td><s:checkbox name="chkbox" value="6"/></td>
                        <td><label>Maklumat Atas Tanah</label></td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                     <tr>
                        <td><s:checkbox name="chkbox" id="chkbox7" onclick="doEnabled('7')" value="7"/></td>
                        <td><label>Kod Lot</label></td>
                        <td>${actionBean.hakmilik.lot.nama}</td>
                        <td><s:select name="hakmilik.lot.kod" id="elem7">
                                <s:options-collection collection="${list.senaraiKodLot}" label="nama" value="kod"/>
                        </s:select></td>
                    </tr>
                     <tr>
                        <td><s:checkbox name="chkbox" id="chkbox8" onclick="doEnabled('8')" value="8"/></td>
                        <td><label>No Lot</label></td>
                        <td>${actionBean.hakmilik.noLot}</td>
                        <td><s:text name="hakmilik.noLot" id="elem8"/></td>
                    </tr>
                     <tr>
                        <td><s:checkbox name="chkbox" id="chkbox9" onclick="doEnabled('9')" value="9"/></td>
                        <td><label>Keluasan</label></td>
                        <td> ${actionBean.hakmilik.luas}</td>
                        <td><s:text name="hakmilik.luas" id="elem9"/></td>
                    </tr>
                     <tr>
                        <td><s:checkbox name="chkbox" id="chkbox10" onclick="doEnabled('10')" value="10"/></td>
                        <td><label>No Lithosheet</label></td>
                        <td> ${actionBean.hakmilik.noLitho}</td>
                        <td><s:text name="hakmilik.noLitho" id="elem10"/></td>
                    </tr>
                     <tr>
                        <td><s:checkbox name="chkbox" id="chkbox11" onclick="doEnabled('11')" value="11"/></td>
                        <td><label>No Pelan</label></td>
                        <td>${actionBean.hakmilik.noPelan}</td>
                        <td><s:text name="hakmilik.noPelan" disabled="true" id="elem11"/></td>
                    </tr>
                </table>
            </div>--%>

            <p>
                <label for="Amaun Cukai">Amaun Cukai :</label>                
                <s:text name="hakmilik.kadarCukai"/>
            </p>
            <p>
                <label for="Daerah">Daerah :</label>                
                <s:select name="hakmilik.daerah.kod">
                    <s:options-collection collection="${list.senaraiKodDaerah}" label="nama" value="kod"/>
                </s:select>

            </p>
            <p>
                <label for="Daerah">Bandar/Pekan /Mukim :</label>                
                <s:select name="hakmilik.bandarPekanMukim.kod">
                                <s:options-collection collection="${list.senaraiKodBandarMukim}" label="nama" value="kod"/>
                        </s:select>

            </p>
            <p>
                <label for="Daerah">Seksyen :</label>                
                <s:select name="hakmilik.seksyen.kod">
                                <s:options-collection collection="${list.senaraiKodSeksyen}" label="nama" value="kod"/>
                        </s:select>

            </p>
            <p>
                <label for="Daerah">Jenis Hakmilik :</label>
                &nbsp;
            </p>
            <p>
                <label for="Daerah">Kategori Tanah :</label>
                &nbsp;
            </p>
            <p>
                <label for="Daerah">Unit Luas :</label>
                <s:select name="hakmilik.kodUnitLuas.kod">
                                <s:options-collection collection="${list.senaraiKodUOM}" label="nama" value="kod"/>
                        </s:select>
            </p>
            <p>
                <label for="Daerah">Maklumat Atas Tanah :</label>
                <s:text name="hakmilik.atasTanah"/>
            </p>
            <p>
                <label for="Daerah">Kategori Tanah :</label>
                <s:select name="hakmilik.kategoriTanah.kod">
                                <s:options-collection collection="${list.senaraiKodKatagoriTanah}" label="nama" value="kod"/>
                        </s:select>
            </p>
            <p>
                <label for="Daerah">Lot :</label>               
                <s:select name="hakmilik.lot.kod">
                                <s:options-collection collection="${list.senaraiKodLot}" label="nama" value="kod"/>
                        </s:select>
            </p>
            <p>
                <label for="Daerah">No Lot :</label>                
                <s:text name="hakmilik.noLot"/>

            </p>
            <p>
                <label for="Daerah">Keluasan :</label> 
                <s:text name="hakmilik.luas"/>

            </p>
            <p>
                <label for="Daerah">No Lithosheet :</label>              
                <s:text name="hakmilik.noLitho"/>

            </p>
            <p>
                <label for="Daerah">No Pelan :</label>               
                <s:text name="hakmilik.noPelan" />
            </p>

        </fieldset>
    </div>

    <p>
        <label>&nbsp;</label>
        <s:button name="update" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"/>
    </p>
</s:form>
